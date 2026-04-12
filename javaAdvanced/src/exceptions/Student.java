package exceptions;

import java.sql.SQLException;

public class Student {


    public int findStudentByRollNo(int n) throws SQLException {

        try{
            if(n<50){
                int a=2,b=0;
                if(b!=0) return a/b;

                throw new SQLException();
            }
            else if(n%2==0){
                throw new EvenException();
            }
            else if(n%2==1){
                throw new OddException();
            }
            else {
                throw new IndexOutOfBoundsException();
            }

        }
        catch (SQLException sql){
            /*
            * fit a cron por lambda here so here we can do 2-3 reties before throwing the exception;
            *
            * */
            System.out.println("Im a sql exception handle me");
            throw new SQLException();
        }catch (EvenException evex){
            System.out.println("Im a even exception handle me");
        }catch (OddException odex){
            System.out.println("Im a odd exception handle me");
        }catch (Exception excp){
            System.out.println("all exceptions");
        }finally {
            System.out.println("Finally im free");
        }



//        cron jobs?

        return 0;



    }
}
