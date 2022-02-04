package pe.izipay.common.core.helpers;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.reactive.context.StandardReactiveWebEnvironment;
import pe.izipay.common.core.exceptions.AppRuntimeException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileHelperTest {

    @Test
    void testDeleteFile() {
        String folder = "PathBase1";
        String fileName = "foo1.txt";
        String fileContent = "Not all who wander are lost";
        String filePath = FileHelper.createFileInPath(folder, fileName, fileContent);
        assertDoesNotThrow(() -> FileHelper.deleteFile(Paths.get(filePath)));
        assertDoesNotThrow(() -> FileHelper.deleteDirectory(new File(folder)));
    }

    @Test
    void testDeleteFile1() {
        var file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.deleteFile(file));
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.deleteFile(file));
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.deleteFile(file));
    }

    @Test
    void testCreateDirectoryFromFile() throws IOException {
        String folder = "PathBase2";
        String fileName = "foo2.txt";
        String fileContent = "Not all who wander are lost";
        String filePath = FileHelper.createFileInPath(folder, fileName, fileContent);
        FileHelper.createDirectoryFromFile(Paths.get(filePath).toFile());
        Assertions.assertEquals("Not all who wander are lost", FileHelper.readStringFromFile(Paths.get(filePath).toFile()));

        assertDoesNotThrow(() -> FileHelper.deleteDirectory(new File(folder)));
    }

    @Test
    void testCreateFileInPath() {
        String folder = "PathBase3";
        String fileName = "foo3.txt";
        String fileContent = "Not all who wander are lost";
        String filePath = FileHelper.createFileInPath(folder, fileName, fileContent);
        assertTrue(filePath.contains(Path.of(folder, fileName).toString()));
        assertThrows(AppRuntimeException.class, () -> FileHelper.createFileInPath((File) null, null));
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.createFileInPath("", "foo.txt", "Not all who wander are lost"));

        assertDoesNotThrow(() -> FileHelper.deleteDirectory(new File(folder)));
    }

    @Test
    void testCreateFileInPath2() {
        String folder = "PathBase4";
        String fileName = "foo4.txt";
        String fileContent = "Not all who wander are lost";
        String fileWithFolder = String.format("%s/%s", folder,fileName);
        assertDoesNotThrow(() -> FileHelper.createFileInPath(fileWithFolder, fileContent));
        assertThrows(NullPointerException.class, () -> FileHelper.createFileInPath((String) null, null));
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.createFileInPath("foo.txt", "Not all who wander are lost"));

        assertDoesNotThrow(() -> FileHelper.deleteDirectory(new File(folder)));
    }

    @Test
    void testReadStringFromFile() {
        var path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
        var file = path.toFile();
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.readStringFromFile(file));
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.readStringFromFile(path));
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.readStringFromFile(file));
        assertThrows(AppRuntimeException.class,
                () -> FileHelper.readStringFromFile(path));
    }

    @Test
    void testCreateDirectoryIfNotExist() {
        Assertions.assertFalse(FileHelper.createDirectoryIfNotExist(null));
        assertTrue(FileHelper.createDirectoryIfNotExist("Path"));
    }


    @Test
    void testCreateFolderPath() {
//        assertThrows(AppRuntimeException.class, () -> FileHelper.createFolderPath(Path.of("C:\\system32\\")));
        assertThrows(Exception.class, () -> FileHelper.createFolderPath((String) null));
//        assertThrows(AppRuntimeException.class, () -> FileHelper.createFolderPath("Path"));
        assertDoesNotThrow(() -> FileHelper.createFolderPath("Path"));

    }

    @Test
    void testGetFileFromCurrentDirectory() {
        assertTrue(FileHelper.getFileFromCurrentDirectory().length() > 0);
    }

    @Test
    void testGetCurrentDirectory() {
        Assertions.assertEquals(System.getProperty("user.dir"), FileHelper.getCurrentDirectory());
        assertEquals(System.getProperty("user.dir"), FileHelper.getCurrentDirectory());
    }

    @Test
    void testGetCurrentParentDirectory() {
        String actualCurrentParentDirectory = FileHelper.getCurrentParentDirectory();
        assertThat(actualCurrentParentDirectory).hasSizeGreaterThan(0);
    }

    @Test
    void testGetCurrentParentDirectory2() {
        String actualCurrentParentDirectory = FileHelper.getCurrentParentDirectory();
        assertFalse(actualCurrentParentDirectory.contains("back_commons-tests/src"));
    }

    @Test
    void testGetTemporaryDirectory() {
        Assertions.assertEquals(System.getProperty("java.io.tmpdir"), FileHelper.getTemporaryDirectory());
        assertEquals(System.getProperty("java.io.tmpdir"), FileHelper.getTemporaryDirectory());
    }

    @Test
    void testIsFileResource() {
        Assertions.assertFalse(FileHelper.isFileResource("Path"));
        Assertions.assertTrue(FileHelper.isFileResource("classpath:"));
        assertFalse(FileHelper.isFileResource("Path"));
        assertTrue(FileHelper.isFileResource("classpath:"));
    }

    @Test
    void testLoadFileResource() {
        Assertions.assertDoesNotThrow(() -> FileHelper.loadFileResource("Path"));
        Assertions.assertDoesNotThrow(() -> FileHelper.loadFileResource("classpath"));
    }

    @Test
    void testLoadFileResource2() {
        assertThrows(AppRuntimeException.class, () -> FileHelper.loadFileResource("classpath:Path"));
    }

    @Test
    void testLoadFileResource3() {
        assertThrows(AppRuntimeException.class, () -> FileHelper.loadFileResource("classpath:Path"));
    }

    @Test
    void testFormatPath() {
        assertDoesNotThrow(() -> {
            FileHelper.formatPath("Folder");
            FileHelper.formatPath("");
            FileHelper.formatPath("Folder", true);
            FileHelper.formatPath("", true);
            FileHelper.formatPath("Folder", false);
        });

    }

    @Test
    void testFormatPath2() {
        Assertions.assertDoesNotThrow(() -> FileHelper.formatPath(null));
        Assertions.assertDoesNotThrow(() -> FileHelper.formatPath(null, true));
        Assertions.assertDoesNotThrow(() -> FileHelper.formatPath(null, false));
    }


    @Test
    void testFormatPathString() {
        Assertions.assertEquals("Folder", FileHelper.formatPathString("Folder"));
        Assertions.assertEquals(System.getProperty("user.dir"), FileHelper.formatPathString(""));
        assertEquals("Folder", FileHelper.formatPathString("Folder"));
        assertEquals(System.getProperty("user.dir"), FileHelper.formatPathString(""));
    }

    @Test
    void testReadProperties() {
        String folder = "PathBase5";
        String fileName = "foo5.txt";
        String fileContent = "Not all who wander are lost";
        String filePath = FileHelper.createFileInPath(folder, fileName, fileContent);
        Assertions.assertFalse(
                FileHelper.readProperties(Paths.get(filePath).toFile()).isEmpty());

        assertDoesNotThrow(() -> FileHelper.deleteFile(Paths.get(filePath)));
        Assertions.assertTrue(
                FileHelper.readProperties(Paths.get(filePath).toFile()).isEmpty());


        Assertions.assertTrue(
                FileHelper.readProperties(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()).isEmpty());
        assertTrue(
                FileHelper.readProperties(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()).isEmpty());
    }

    @Test
    void testFormatPathEnviroment() {
        assertDoesNotThrow(() -> {
            FileHelper.formatPathEnviroment("Folder", new StandardReactiveWebEnvironment());
            FileHelper.formatPathEnviroment(null, new StandardReactiveWebEnvironment());
            FileHelper.formatPathEnviroment("", new StandardReactiveWebEnvironment());
            FileHelper.formatPathEnviroment("./", new StandardReactiveWebEnvironment());
            FileHelper.formatPathEnviroment("*/", new StandardReactiveWebEnvironment());
            FileHelper.formatPathEnviroment("../", new StandardReactiveWebEnvironment());
            FileHelper.formatPathEnviroment("?/", new StandardReactiveWebEnvironment());
           // formatPathEnviroment(":/", new StandardReactiveWebEnvironment());
            FileHelper.formatPathEnviroment("/$/", new StandardReactiveWebEnvironment());
        });
    }


    @Test
    void testFormatPathEnviromentString() {
        Assertions.assertEquals("Folder", FileHelper.formatPathEnviromentString("Folder", new StandardReactiveWebEnvironment()));
        Assertions.assertEquals(System.getProperty("user.dir"),
                FileHelper.formatPathEnviromentString(null, new StandardReactiveWebEnvironment()));
        Assertions.assertEquals(System.getProperty("user.dir"),
                FileHelper.formatPathEnviromentString("", new StandardReactiveWebEnvironment()));
        assertEquals("Folder", FileHelper.formatPathEnviromentString("Folder", new StandardReactiveWebEnvironment()));
        assertEquals(System.getProperty("user.dir"),
                FileHelper.formatPathEnviromentString(null, new StandardReactiveWebEnvironment()));
        assertEquals(System.getProperty("user.dir"),
                FileHelper.formatPathEnviromentString("", new StandardReactiveWebEnvironment()));
    }

    @Test
    void textDeleteDirectory() {
        String folder = "PathBase6";
        String fileName = "foo6.txt";
        String fileContent = "Not all who wander are lost";
        String filePath = FileHelper.createFileInPath(folder, fileName, fileContent);
        assertDoesNotThrow(() -> FileHelper.deleteFile(Path.of(filePath)));

        assertDoesNotThrow(() -> FileHelper.deleteDirectory(new File(folder)));
        assertThrows(NullPointerException.class, () -> FileHelper.deleteDirectory(null));
    }

    @Test
    void textDeleteDirectory1(){
        assertDoesNotThrow(() ->FileHelper.createDirectoryFromFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
    }

    @Test
    void testCopyFile() {
        String folder = "PathBase7";
        String fileName = "foo7.txt";
        String fileContent = "Not all who wander are lost";
        String filePath = FileHelper.createFileInPath(folder, fileName, fileContent);
        String parentPath = new File(filePath).getParent();
        assertDoesNotThrow(() -> FileHelper.copyFile(Path.of(filePath), Path.of(parentPath + "/Base1")));
        assertDoesNotThrow(() -> FileHelper.copyFile(Path.of(filePath), Path.of(filePath)));
        assertDoesNotThrow(() -> FileHelper.copyFile(Path.of(parentPath), Path.of(parentPath)));
        assertDoesNotThrow(() -> FileHelper.deleteDirectory(new File(folder)));
        var file = Path.of(filePath);
        var parent = Path.of(parentPath);
        assertThrows(AppRuntimeException.class, () -> FileHelper.copyFile(file, parent));
    }

    @Test
    void testCopyFile1() {
        String folder = "PathBase8";
        String fileName = "foo8.txt";
        String fileContent = "Not all who wander are lost";
        String filePath = FileHelper.createFileInPath(folder, fileName, fileContent);
        String parentPath = new File(filePath).getParent();
        Assertions.assertDoesNotThrow(() -> FileHelper.copyFile(new File(filePath), parentPath + "/Base1", "foo_copy.txt"));
        assertDoesNotThrow(() -> FileHelper.deleteDirectory(new File(folder)));
    }
}

