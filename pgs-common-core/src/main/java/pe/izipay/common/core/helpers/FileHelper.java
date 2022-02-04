package pe.izipay.common.core.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ResourceUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.types.RelativePathType;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileHelper {

	private static final String CLASSPATH_PREFIX = "classpath:";
	
    public static void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException ex) {
            throw new AppRuntimeException(ex);
        }
    }

    public static void createDirectoryFromFile(File file) throws IOException {
        Path path = Path.of(file.getParent());
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    public static void createFileInPath(File file, String content) {
        try {
            createDirectoryFromFile(file);
            try (FileWriter writer = new FileWriter(file)) {
                writer.append(content);
                writer.flush();
            }
        } catch (Exception ex) {
            throw new AppRuntimeException(ex);
        }
    }

    public static void createFileInPath(String pathFile, String content) {
        createFileInPath(new File(pathFile), content);
    }

    public static String createFileInPath(String pathBase, String fileName, String content) {
        File file = new File(pathBase, fileName);
        createFileInPath(file, content);
        return file.getAbsolutePath();
    }

    public static void copyFile(Path source, Path target) {
        try {
            createDirectoryIfNotExist(target.getParent().toString());
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new AppRuntimeException(ex);
        }
    }

    public static Path copyFile(File source, String rootDirectory, String target) {
        var path = Path.of(rootDirectory, target);
        copyFile(Path.of(source.getAbsolutePath()), path);
        return path;
    }

    public static String readStringFromFile(Path source) {
        try {
            return Files.readString(source);
        } catch (IOException ex) {
            throw new AppRuntimeException(ex);
        }
    }

    public static String readStringFromFile(File source) {
        return readStringFromFile(Path.of(source.getAbsolutePath()));
    }

    public static boolean createDirectoryIfNotExist(String path) {
        try {
            File directory = new File(path);
            if (!directory.exists()) {
                return directory.mkdirs();
            }
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    public static void createFolderPath(Path path) {
        try {
            Files.createDirectories(path);
        } catch (Exception ex) {
            throw new AppRuntimeException(ex);
        }
    }

    public static void createFolderPath(String path) {
        createFolderPath(Path.of(path));
    }

    public static File getFileFromCurrentDirectory() {
        return new File(new FileSystemResource("").getFile().getAbsolutePath());
    }

    public static String getCurrentDirectory() {
        return getFileFromCurrentDirectory().getAbsolutePath();
    }

    public static String getCurrentParentDirectory() {
        return getFileFromCurrentDirectory().getParent();
    }

    public static String getTemporaryDirectory() {
        return System.getProperty("java.io.tmpdir");
    }
    
    public static boolean isFileResource(String path) {
    	return path.startsWith(CLASSPATH_PREFIX);
    }
    
    public static File loadFileResource(String path) {
        try {
			return ResourceUtils.getFile(path);
		} catch (Exception ex) {
			throw new AppRuntimeException(ex);
		}
    }
    
    public static Path formatPath(String folder, boolean useEnviroments) {
        if (TextHelper.isNullOrEmpty(folder)) {
        	return Paths.get(FileHelper.getCurrentDirectory());
        } else if (folder.startsWith(RelativePathType.CURRENT.prefix())) {
            return Paths.get(FileHelper.getCurrentDirectory(), 
            		folder.substring(RelativePathType.CURRENT.prefix().length()));
        } else if (folder.startsWith(RelativePathType.PARENT_CURRENT.prefix())) {
            return Path.of(FileHelper.getCurrentParentDirectory(), 
            		folder.substring(RelativePathType.PARENT_CURRENT.prefix().length()));
        } else if (folder.startsWith(RelativePathType.CLASSPATH.prefix())) {
            return Path.of(CLASSPATH_PREFIX + folder.substring(RelativePathType.CLASSPATH.prefix().length()));
        } else if (folder.startsWith(RelativePathType.TEMPORARY.prefix())) {
            return Path.of(FileHelper.getTemporaryDirectory(), 
            		folder.substring(RelativePathType.TEMPORARY.prefix().length()));
        } else if (folder.startsWith(RelativePathType.RELATIVE.prefix())) {
        	var path = Path.of(FileHelper.getCurrentDirectory());
        	while(folder.startsWith(RelativePathType.RELATIVE.prefix())) {
        		path = path.getParent();
        		folder = folder.substring(RelativePathType.RELATIVE.prefix().length());        		
        	}
        	return Path.of(path.toString(), folder);
        } else if (useEnviroments && folder.startsWith(RelativePathType.ENVIROMENT.prefix())) {
        	return null;
        } else {
            return Path.of(folder);
        }        
    }
    
    public static Path formatPathEnviroment(String folder, Environment enviroment) {
		Path path = FileHelper.formatPath(folder, true);
		if(path == null) {
			path = Path.of(enviroment.getProperty(folder.substring(RelativePathType.ENVIROMENT.prefix().length())));
		}
		return path;
	}
    
    public static String formatPathEnviromentString(String folder, Environment enviroment) {
    	return formatPathEnviroment(folder, enviroment).toString();
    }
    
    public static Path formatPath(String folder) {
    	return formatPath(folder, false);
    }
    
    public static String formatPathString(String folder) {
    	return formatPath(folder).toString();
    }

    public static  void deleteDirectory(File directoryToBeDeleted) throws IOException {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        Files.delete(directoryToBeDeleted.toPath());
    }

    public static Properties readProperties(File file){
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            properties.load(reader);
        } catch (Exception ex) {
            log.error(ex);
        }
        return properties;
    }
}