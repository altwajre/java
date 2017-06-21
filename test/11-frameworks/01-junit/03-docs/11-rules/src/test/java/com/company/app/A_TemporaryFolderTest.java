package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
The TemporaryFolder Rule allows creation of files and folders that are deleted when the test method finishes
(whether it passes or fails). By default no exception is thrown if resources cannot be deleted
 */
public class A_TemporaryFolderTest {

  @Rule
  public final TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testUsingTempFolder() throws IOException {

    File createdFile = folder.newFile("myfile.txt");
    File createdFolder = folder.newFolder("subfolder");

    assertTrue(createdFile.exists());
    assertEquals("myfile.txt", createdFile.getName());

    assertTrue(createdFolder.exists());
    assertEquals("subfolder", createdFolder.getName());
  }

}
