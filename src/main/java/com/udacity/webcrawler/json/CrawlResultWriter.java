package com.udacity.webcrawler.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Utility class to write a {@link CrawlResult} to file.
 */
public final class CrawlResultWriter {
  private final CrawlResult result;

  /**
   * Creates a new {@link CrawlResultWriter} that will write the given {@link CrawlResult}.
   */
  public CrawlResultWriter(CrawlResult result) {
    this.result = Objects.requireNonNull(result);
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Path}.
   *
   * <p>If a file already exists at the path, the existing file should not be deleted; new data
   * should be appended to it.
   *
   * @param path the file path where the crawl result data should be written.
   */
  public void write(Path path){
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(path);
    // TODO: Fill in this method.
    try (Writer writer = Files.newBufferedWriter(path)) {
      write(writer);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Writer}.
   *
   * @param writer the destination where the crawl result data should be written.
   */
  public void write(Writer writer) {
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(writer);
    // TODO: Fill in this method.

    JsonFactory jsonFactory = new JsonFactory();
    jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
    try{
      objectMapper.registerModule(new JavaTimeModule());
      objectMapper.writeValue(writer, result);
    } catch (IOException e){
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    //System.out.println("jjjj");
    Path path = Paths.get("hello.txt");
    try(Writer w = Files.newBufferedWriter(path)){
      w.write("Hello world");
    }catch(IOException e) {
      e.printStackTrace();
    };

    char[] data = new char[100];
    try(Reader reader = Files.newBufferedReader(Path.of("Hello.txt"))){
      while (reader.read(data) != -1) {
        System.out.println(data);
      }
    } catch(IOException e){
      e.printStackTrace();
    };

  }
}

