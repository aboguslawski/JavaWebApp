//package com.aboguslawski.blog.util;
//
//import com.aboguslawski.blog.model.OldPost;
//import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Reader;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class OpenCSVReader {
//
//    private static final String POSTS_PATH = "C:\\Users\\Adam Bogusławski\\Desktop\\Programowanie\\Java\\blog\\src\\main\\resources\\OnePosts.csv";
//
//    public static void postsData(List<OldPost> sourceList) throws IOException {
//        int postId;
//        String postUser;
//        String postContent;
//
//        Reader reader = Files.newBufferedReader(Paths.get(POSTS_PATH));
//        CSVReader csvReader = new CSVReader(reader);
//        String[] nextRecord;
//        csvReader.readNext();
//
//        while ((nextRecord = csvReader.readNext()) != null) {
//            postId = Integer.parseInt(nextRecord[0]);
//            postUser = nextRecord[1];
//            postContent = nextRecord[2];
//
//            sourceList.add(new OldPost(postId, postUser, postContent));
//        }
//    }
//
//    public static void addPost(OldPost oldPost) throws IOException{
//        CSVWriter writer = new CSVWriter(new FileWriter(POSTS_PATH, true));
//
//        String[] record = (oldPost.getId() + "," + oldPost.getUser() + "," + oldPost.getContent()).split(",");
//
//        writer.writeNext(record);
//
//        writer.close();
//
//    }
//
//}
