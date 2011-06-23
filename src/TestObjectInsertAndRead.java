import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectInserter;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.ObjectStream;
import org.eclipse.jgit.storage.file.FileRepository;


public class TestObjectInsertAndRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Get FileRepository
			FileRepository fRepo = new FileRepository(new File("/tmp/dir/.git"));
			
			// Get ObjectInserter and ObjectReader
			ObjectInserter inserter = fRepo.newObjectInserter();
			ObjectReader reader = fRepo.newObjectReader();
			
			// Insert Object
			File inFile = new File("/tmp/filex");
			long fSize = inFile.length();
			FileInputStream fis = new FileInputStream(inFile);
			ObjectId objId = inserter.insert(Constants.OBJ_BLOB, fSize, fis);
			
			// Make output file
			File outFile = new File("/tmp/filey");
			if(!outFile.exists()) outFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outFile);
			
			// Read Object
			ObjectLoader ldr = reader.open(objId);
			ObjectStream objStream = ldr.openStream();
			byte[] buff = new byte[1024];
			int len;
			while((len = objStream.read(buff)) != -1){
				fos.write(buff, 0, len);
			}
			fos.close();
			objStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
