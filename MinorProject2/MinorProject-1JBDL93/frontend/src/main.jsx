import React, { useEffect, useMemo, useState } from 'react';
import { createRoot } from 'react-dom/client';
import './styles.css';

const API_BASE = '/api';
const TOKEN_KEY = 'aurelia.jwt';
const ROLE_KEY = 'aurelia.role';
const genres = ['PHYSICS', 'CHEMISTRY', 'MATHS', 'MUSIC', 'OTHERS'];
const genders = ['MALE', 'FEMALE'];

function App() {
  const [token, setToken] = useStoredState(TOKEN_KEY, '');
  const [role, setRole] = useStoredState(ROLE_KEY, '');
  const [view, setView] = useState(token ? 'dashboard' : 'welcome');
  const [busy, setBusy] = useState(false);
  const [notice, setNotice] = useState({ type: 'calm', text: 'Create an account or sign in to enter the library.' });
  const [books, setBooks] = useState([]);
  const [profile, setProfile] = useState(null);
  const [query, setQuery] = useState('');
  const [genre, setGenre] = useState('ALL');

  const isAdmin = role === 'ADMIN';
  const isStudent = role === 'STUDENT';
  const signedIn = Boolean(token);

  const filteredBooks = useMemo(() => {
    const term = query.trim().toLowerCase();
    return books.filter(book => {
      const haystack = `${book.name || ''} ${book.genre || ''} ${book.author?.name || ''}`.toLowerCase();
      return (genre === 'ALL' || book.genre === genre) && (!term || haystack.includes(term));
    });
  }, [books, query, genre]);

  const issuedBooks = useMemo(() => {
    if (!profile?.id) return [];
    return books.filter(book => book.student?.id === profile.id);
  }, [books, profile]);

  async function request(path, options = {}) {
    const headers = { ...(options.headers || {}) };
    if (options.body) headers['Content-Type'] = 'application/json';
    if (token && options.auth !== false) headers.Authorization = `Bearer ${token}`;

    setBusy(true);
    try {
      const response = await fetch(`${API_BASE}${path}`, { ...options, headers });
      const contentType = response.headers.get('content-type') || '';
      const data = contentType.includes('application/json') ? await response.json() : await response.text();
      if (!response.ok) throw new Error(typeof data === 'string' && data ? data : `Request failed with ${response.status}`);
      return data;
    } catch (error) {
      setNotice({ type: 'danger', text: error.message });
      return null;
    } finally {
      setBusy(false);
    }
  }

  async function login(credentials, selectedRole) {
    const data = await request('/login', { method: 'POST', auth: false, body: JSON.stringify(credentials) });
    if (!data?.token) return;

    const tokenRole = getRoleFromToken(data.token) || selectedRole;
    if (selectedRole && tokenRole !== selectedRole) {
      setNotice({ type: 'danger', text: `This account is ${tokenRole}. Select the correct login type.` });
      return;
    }

    setToken(data.token);
    setRole(tokenRole);
    setView('dashboard');
    setNotice({ type: 'success', text: `${formatEnum(tokenRole)} login successful.` });
  }

  async function createStudent(payload) {
    const data = await request('/student/create', { method: 'POST', auth: false, body: JSON.stringify(clean(payload)) });
    if (data?.student) {
      setNotice({ type: 'success', text: 'Student account created. Sign in with your username and password.' });
      setView('signin');
    }
  }

  async function createAdmin(payload) {
    const data = await request('/admin/create', { method: 'POST', body: JSON.stringify(clean(payload)) });
    if (data) setNotice({ type: 'success', text: `Admin account created with ID ${data}.` });
  }

  async function loadBooks(showMessage = false) {
    if (!token) return;
    const data = await request('/book/list');
    if (Array.isArray(data)) {
      setBooks(data);
      if (showMessage) setNotice({ type: 'success', text: `Catalog refreshed: ${data.length} books found.` });
    }
  }

  async function loadProfile() {
    if (!token || !isStudent) return;
    const data = await request('/student/get');
    if (data?.student) setProfile(data.student);
  }

  async function createBook(payload) {
    const data = await request('/book/create', { method: 'POST', body: JSON.stringify(clean(payload)) });
    if (data?.bookId) {
      setNotice({ type: 'success', text: `Book added successfully. Catalog ID ${data.bookId}.` });
      await loadBooks(false);
    }
  }

  async function transact(bookId, transactionType) {
    const label = transactionType === 'ISSUANCE' ? 'issued' : 'returned';
    const data = await request(`/txn/initiate?bookId=${encodeURIComponent(bookId)}&transactionType=${transactionType}`, { method: 'POST' });
    if (data) {
      setNotice({ type: 'success', text: `Book ${label}. Transaction ID: ${data}` });
      await loadBooks(false);
      await loadProfile();
    }
  }

  function logout() {
    setToken('');
    setRole('');
    setProfile(null);
    setBooks([]);
    setView('welcome');
    setNotice({ type: 'calm', text: 'Signed out.' });
  }

  useEffect(() => {
    if (!token) return;
    loadBooks(false);
    loadProfile();
  }, [token, role]);

  return (
    <main className="app-shell">
      <Hero signedIn={signedIn} role={role} profile={profile} onGo={setView} onLogout={logout} />
      <Notice notice={notice} />

      {!signedIn ? (
        <GuestFlow view={view} setView={setView} busy={busy} onLogin={login} onCreateStudent={createStudent} />
      ) : (
        <Dashboard
          view={view}
          setView={setView}
          role={role}
          isAdmin={isAdmin}
          isStudent={isStudent}
          busy={busy}
          books={filteredBooks}
          allBooks={books}
          profile={profile}
          issuedBooks={issuedBooks}
          query={query}
          genre={genre}
          setQuery={setQuery}
          setGenre={setGenre}
          onRefresh={() => loadBooks(true)}
          onIssue={id => transact(id, 'ISSUANCE')}
          onReturn={id => transact(id, 'RETURN')}
          onCreateBook={createBook}
          onCreateAdmin={createAdmin}
        />
      )}
    </main>
  );
}

function Hero({ signedIn, role, profile, onGo, onLogout }) {
  return (
    <section className="hero-panel">
      <div className="hero-copy">
        <span className="eyebrow">Aurelia E-Library</span>
        <h1>A dark, modern library desk for students and admins.</h1>
        <p>Students browse available books and manage returns. Admins maintain the catalog and onboard staff.</p>
        <div className="hero-actions">
          <button onClick={() => onGo(signedIn ? 'dashboard' : 'signup')}>Get started</button>
          <button className="ghost" onClick={() => onGo(signedIn ? 'catalog' : 'signin')}>{signedIn ? 'Open catalog' : 'Sign in'}</button>
        </div>
      </div>
      <aside className="identity-card">
        <span>{signedIn ? formatEnum(role) : 'Not signed in'}</span>
        <strong>{profile?.name || (signedIn ? 'Library session active' : 'Choose your account')}</strong>
        <p>{signedIn ? 'Your permissions decide what actions are visible.' : 'Create student account publicly. Admin creation requires an existing admin login.'}</p>
        {signedIn ? <button className="ghost" onClick={onLogout}>Logout</button> : null}
      </aside>
    </section>
  );
}

function Notice({ notice }) {
  return <div className={`notice ${notice.type}`}>{notice.text}</div>;
}

function GuestFlow({ view, setView, busy, onLogin, onCreateStudent }) {
  return (
    <section className="guest-grid">
      <article className="choice-panel panel">
        <span className="eyebrow">Step 1</span>
        <h2>Who are you?</h2>
        <div className="role-cards">
          <button className="role-card" onClick={() => setView('signup')}>
            <b>Student</b>
            <span>Create account, browse books, issue and return.</span>
          </button>
          <button className="role-card" onClick={() => setView('signin-admin')}>
            <b>Admin</b>
            <span>Login to add books and create more admins.</span>
          </button>
        </div>
      </article>
      {view === 'signup' || view === 'welcome' ? <StudentSignup busy={busy} onSubmit={onCreateStudent} onSignin={() => setView('signin')} /> : null}
      {view === 'signin' ? <Signin title="Student login" role="STUDENT" busy={busy} onSubmit={onLogin} /> : null}
      {view === 'signin-admin' ? <Signin title="Admin login" role="ADMIN" busy={busy} onSubmit={onLogin} /> : null}
    </section>
  );
}

function Dashboard(props) {
  const nav = props.isAdmin
    ? [['dashboard', 'Overview'], ['catalog', 'Catalog'], ['books', 'Add Book'], ['admins', 'Add Admin']]
    : [['dashboard', 'Overview'], ['catalog', 'Catalog'], ['my-books', 'My Books']];

  return (
    <>
      <nav className="dashboard-tabs">
        {nav.map(([key, label]) => <button key={key} className={props.view === key ? 'active' : ''} onClick={() => props.setView(key)}>{label}</button>)}
      </nav>
      {props.view === 'dashboard' ? <Overview {...props} /> : null}
      {props.view === 'catalog' ? <Catalog {...props} /> : null}
      {props.view === 'my-books' && props.isStudent ? <MyBooks {...props} /> : null}
      {props.view === 'books' && props.isAdmin ? <BookForm busy={props.busy} onSubmit={props.onCreateBook} /> : null}
      {props.view === 'admins' && props.isAdmin ? <AdminForm busy={props.busy} onSubmit={props.onCreateAdmin} /> : null}
    </>
  );
}

function Overview({ role, allBooks, issuedBooks, isAdmin, isStudent, setView }) {
  const available = allBooks.filter(book => !book.student).length;
  return (
    <section className="overview-grid">
      <Stat label="Signed in as" value={formatEnum(role)} />
      <Stat label="Total books" value={allBooks.length} />
      <Stat label="Available now" value={available} />
      {isStudent ? <Stat label="Issued to you" value={issuedBooks.length} /> : <Stat label="Admin tools" value="Enabled" />}
      <article className="panel wide-card">
        <span className="eyebrow">Next action</span>
        <h2>{isAdmin ? 'Keep the catalog useful.' : 'Find your next book.'}</h2>
        <p>{isAdmin ? 'Add clean book records with author and subject so students can search without asking staff.' : 'Search the catalog, issue available books, and return books from your My Books section.'}</p>
        <button onClick={() => setView(isAdmin ? 'books' : 'catalog')}>{isAdmin ? 'Add a book' : 'Browse catalog'}</button>
      </article>
    </section>
  );
}

function Catalog({ books, allBooks, profile, isStudent, isAdmin, busy, query, genre, setQuery, setGenre, onRefresh, onIssue, onReturn }) {
  return (
    <section className="panel catalog-panel">
      <div className="panel-heading">
        <div><span className="eyebrow">Catalog</span><h2>All books in the library</h2></div>
        <button className="ghost" disabled={busy} onClick={onRefresh}>Refresh</button>
      </div>
      <div className="filters">
        <input value={query} onChange={event => setQuery(event.target.value)} placeholder="Search title, author, subject..." />
        <select value={genre} onChange={event => setGenre(event.target.value)}>
          <option value="ALL">All subjects</option>
          {genres.map(item => <option key={item} value={item}>{formatEnum(item)}</option>)}
        </select>
      </div>
      <div className="catalog-summary">Showing {books.length} of {allBooks.length} books</div>
      <div className="book-grid">
        {books.map(book => <BookCard key={book.id} book={book} profile={profile} isStudent={isStudent} isAdmin={isAdmin} busy={busy} onIssue={onIssue} onReturn={onReturn} />)}
      </div>
      {!books.length ? <Empty title="No books found" text="Try another search or ask admin to add books." /> : null}
    </section>
  );
}

function BookCard({ book, profile, isStudent, isAdmin, busy, onIssue, onReturn }) {
  const issuedTo = book.student?.id;
  const mine = profile?.id && issuedTo === profile.id;
  const available = !issuedTo;
  const status = available ? 'Available' : mine ? 'Issued to you' : 'Issued';

  return (
    <article className="book-card">
      <div className="book-spine"><span>{(book.name || 'B').slice(0, 1)}</span></div>
      <div className="book-content">
        <div className="book-top"><span>{formatEnum(book.genre || 'Book')}</span><b className={available ? 'ok' : mine ? 'mine' : 'locked'}>{status}</b></div>
        <h3>{book.name}</h3>
        <p>{book.author?.name || 'Unknown author'}</p>
        <small>Book ID {book.id}</small>
      </div>
      {isStudent ? (
        <div className="book-actions">
          {available ? <button disabled={busy} onClick={() => onIssue(book.id)}>Issue book</button> : null}
          {mine ? <button className="ghost" disabled={busy} onClick={() => onReturn(book.id)}>Return book</button> : null}
          {!available && !mine ? <button className="ghost" disabled>Unavailable</button> : null}
        </div>
      ) : null}
      {isAdmin ? <div className="admin-note">{available ? 'Ready for students' : `With student #${issuedTo}`}</div> : null}
    </article>
  );
}

function MyBooks({ issuedBooks, busy, onReturn }) {
  return (
    <section className="panel">
      <span className="eyebrow">Student Shelf</span>
      <h2>Books issued to you</h2>
      <div className="issued-list">
        {issuedBooks.map(book => (
          <div className="issued-row" key={book.id}>
            <div><b>{book.name}</b><span>{book.author?.name || 'Unknown author'} · ID {book.id}</span></div>
            <button className="ghost" disabled={busy} onClick={() => onReturn(book.id)}>Return</button>
          </div>
        ))}
      </div>
      {!issuedBooks.length ? <Empty title="No books issued" text="Go to Catalog and issue an available book." /> : null}
    </section>
  );
}

function StudentSignup({ busy, onSubmit, onSignin }) {
  const [form, setForm] = useState({ name: '', username: '', password: '', email: '', gender: '' });
  return (
    <article className="panel form-panel">
      <span className="eyebrow">Student Signup</span>
      <h2>Create student account</h2>
      <Form onSubmit={() => onSubmit(form)}>
        <Field label="Full name"><input value={form.name} onChange={e => setForm({ ...form, name: e.target.value })} required /></Field>
        <Field label="Username"><input value={form.username} onChange={e => setForm({ ...form, username: e.target.value })} required /></Field>
        <Field label="Password"><input type="password" value={form.password} onChange={e => setForm({ ...form, password: e.target.value })} required /></Field>
        <Field label="Email"><input type="email" value={form.email} onChange={e => setForm({ ...form, email: e.target.value })} /></Field>
        <Select label="Gender" value={form.gender} options={genders} onChange={gender => setForm({ ...form, gender })} />
        <button disabled={busy}>Create account</button>
        <button type="button" className="ghost" onClick={onSignin}>Already registered? Sign in</button>
      </Form>
    </article>
  );
}

function Signin({ title, role, busy, onSubmit }) {
  const [form, setForm] = useState({ username: '', password: '' });
  return (
    <article className="panel form-panel">
      <span className="eyebrow">{formatEnum(role)}</span>
      <h2>{title}</h2>
      <Form onSubmit={() => onSubmit(form, role)}>
        <Field label="Username"><input value={form.username} onChange={e => setForm({ ...form, username: e.target.value })} required /></Field>
        <Field label="Password"><input type="password" value={form.password} onChange={e => setForm({ ...form, password: e.target.value })} required /></Field>
        <button disabled={busy}>Sign in</button>
      </Form>
    </article>
  );
}

function BookForm({ busy, onSubmit }) {
  const [form, setForm] = useState({ name: '', genre: '', authorName: '', email: '' });
  return (
    <article className="panel form-panel slim">
      <span className="eyebrow">Admin Catalog</span>
      <h2>Add a new book</h2>
      <Form onSubmit={() => onSubmit(form)}>
        <Field label="Book title"><input value={form.name} onChange={e => setForm({ ...form, name: e.target.value })} required /></Field>
        <Select label="Subject" value={form.genre} options={genres} onChange={genre => setForm({ ...form, genre })} required />
        <Field label="Author"><input value={form.authorName} onChange={e => setForm({ ...form, authorName: e.target.value })} required /></Field>
        <Field label="Author email"><input type="email" value={form.email} onChange={e => setForm({ ...form, email: e.target.value })} /></Field>
        <button disabled={busy}>Add to catalog</button>
      </Form>
    </article>
  );
}

function AdminForm({ busy, onSubmit }) {
  const [form, setForm] = useState({ name: '', username: '', password: '' });
  return (
    <article className="panel form-panel slim">
      <span className="eyebrow">Admin Staff</span>
      <h2>Create another admin</h2>
      <p className="subtle">Only an already logged-in admin can do this.</p>
      <Form onSubmit={() => onSubmit(form)}>
        <Field label="Name"><input value={form.name} onChange={e => setForm({ ...form, name: e.target.value })} /></Field>
        <Field label="Username"><input value={form.username} onChange={e => setForm({ ...form, username: e.target.value })} required /></Field>
        <Field label="Password"><input type="password" value={form.password} onChange={e => setForm({ ...form, password: e.target.value })} required /></Field>
        <button disabled={busy}>Create admin</button>
      </Form>
    </article>
  );
}

function Stat({ label, value }) {
  return <article className="stat-card"><span>{label}</span><strong>{value}</strong></article>;
}

function Field({ label, children }) {
  return <label className="field"><span>{label}</span>{children}</label>;
}

function Select({ label, value, options, onChange, required }) {
  return (
    <Field label={label}>
      <select value={value} onChange={e => onChange(e.target.value)} required={required}>
        <option value="">Select</option>
        {options.map(option => <option key={option} value={option}>{formatEnum(option)}</option>)}
      </select>
    </Field>
  );
}

function Form({ children, onSubmit }) {
  return <form onSubmit={event => { event.preventDefault(); onSubmit(); }}>{children}</form>;
}

function Empty({ title, text }) {
  return <div className="empty"><b>{title}</b><span>{text}</span></div>;
}

function useStoredState(key, fallback) {
  const [value, setValue] = useState(() => localStorage.getItem(key) || fallback);
  function update(next) {
    setValue(next);
    if (next) localStorage.setItem(key, next);
    else localStorage.removeItem(key);
  }
  return [value, update];
}

function clean(payload) {
  return Object.fromEntries(Object.entries(payload).filter(([, value]) => value !== '' && value !== null && value !== undefined));
}

function getRoleFromToken(token) {
  try {
    const payload = JSON.parse(atob(token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/')));
    const authority = payload.authorities?.[0] || '';
    return authority.replace('ROLE_', '');
  } catch {
    return '';
  }
}

function formatEnum(value) {
  return String(value || '').toLowerCase().replace(/_/g, ' ').replace(/\b\w/g, char => char.toUpperCase());
}

createRoot(document.getElementById('root')).render(<App />);
