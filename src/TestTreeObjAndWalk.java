import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.FileTreeIterator;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.lib.Constants;

public class TestTreeObjAndWalk {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileRepository fRepo;
		try {
			// Get FileRepository
			fRepo = new FileRepository(new File("/tmp/dir/.git"));
			
			// Make TreeWalk
			TreeWalk tw = new TreeWalk(fRepo);
			
			// Get HEAD referring RevCommit Object
			ObjectId headCommitObjId = fRepo.getRef(Constants.HEAD).getLeaf().getObjectId();
			//System.out.println(headCommitObjId.getName());
			ObjectLoader ldr = fRepo.getObjectDatabase().open(headCommitObjId);
			byte[] commitObjBytes = ldr.getBytes();
			RevCommit headCommitObj = RevCommit.parse(commitObjBytes);
			//System.out.println(headCommitObj.getName());
				
			// Get RevTree Object ID
			RevTree headRevTreeObj = headCommitObj.getTree();	// Get RevTree Object
			ldr = fRepo.getObjectDatabase().open(headRevTreeObj);
			byte[] treeObjBytes = ldr.getBytes();	// Get Tree by bytes.
				
			// Make CanonicalTreeParser
			CanonicalTreeParser ctp = new CanonicalTreeParser();
			ctp.reset(treeObjBytes);

			// Add Tree
			tw.addTree(ctp);
				
			// Add Tree
			FileTreeIterator itr = new FileTreeIterator(fRepo);
			tw.addTree(itr);
				
			while(tw.next()){
				System.out.println(tw.getPathString()+":"+tw.getObjectId(0)+","+tw.getObjectId(1));
				if(tw.isSubtree()){
					tw.enterSubtree();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
