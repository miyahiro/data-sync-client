import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.api.errors.JGitInternalException;

public class TestMakeRepositoryAndInit {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Make Directory
			File workTree = new File("/tmp/dir");
			File repoDir = new File("/tmp/dir/.git");
			workTree.mkdir();
			
			// Make FileRepository
			FileRepository fRepo = (new FileRepositoryBuilder())
									.setGitDir(repoDir)
									.setWorkTree(workTree)
									.build();
			fRepo.create();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JGitInternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
