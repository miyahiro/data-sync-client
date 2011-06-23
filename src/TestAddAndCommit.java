import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.storage.file.FileRepository;


public class TestAddAndCommit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileRepository fRepo;
		// Get FileRepository
		try {
			// Get WorkTree Direcotry
			File workTree = new File("/tmp/dir");
			
			// Get Repository
			fRepo = new FileRepository(new File("/tmp/dir/.git"));
			
			// Initialize Git Repository
			Git git = new Git(fRepo);	// Get API
			
			// Make File And Exec git add
			(new File(workTree,"file1")).createNewFile();
			AddCommand addCmd = git.add();
			addCmd.addFilepattern("file1").call();
			
			// Commit
			CommitCommand commitCmd = git.commit();
			commitCmd.setMessage("initial").call();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoFilepatternException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoHeadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConcurrentRefUpdateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JGitInternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongRepositoryStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
