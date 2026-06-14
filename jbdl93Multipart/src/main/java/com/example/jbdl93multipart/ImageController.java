package com.example.jbdl93multipart;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(produces = MediaType.IMAGE_PNG_VALUE)
public class ImageController {


    @GetMapping(value = "/image/{id}")
    public byte[] image(@PathVariable("id") Integer id,
                        @RequestParam(value = "length",required = false,defaultValue = "300") Integer length,
                        @RequestParam(value = "width",required = false,defaultValue = "400") Integer width) {

        String url = "https://picsum.photos/id";//third party integrations

        RestTemplate restTemplate = new RestTemplate(); //third party integrations

        byte[] result = restTemplate.getForObject(url + "/" + id + "/" + length + "/" + width, byte[].class);

        return result;
    }

    @PostMapping("/add/image")
    public byte[] addImage(@RequestBody MultipartFile file) throws IOException {

        Path path = Paths.get("uploads/" + file.getOriginalFilename());
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        return file.getBytes();

    }
}
