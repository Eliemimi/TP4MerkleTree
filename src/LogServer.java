import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.plaf.basic.BasicTreeUI.TreeToggleAction;

//Log server class use a Merkle tree to implement an event log 
public class LogServer {


	// reads a full log from a text file to construct a tree
	public LogServer()  throws NoSuchAlgorithmException {
		File textFile = new File("DS1-trace.txt");
		buildMerkleTree(textFile);
	}

	// Fonctions qui permettent de creer l'arbre
	private static void buildMerkleTree(File file) throws NoSuchAlgorithmException {
		HashMap<MerkleTree,Node> treeToNode=new HashMap<>();
		
		//Lecture du fichier 
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		List<MerkleTree> listOfTree = new ArrayList<>();
		try {
			int index=0;
			while ((line = bufferedReader.readLine()) != null) {
				Leaf leaf = new Leaf(line);
				MerkleTree tree = new MerkleTree(leaf,index,index,leaf.getStringToHashValue().get(line));
				if(!listOfTree.isEmpty()){
					MerkleTree leftTree=listOfTree.get(index-1);
					Node node= new Node(tree,leftTree);
					treeToNode.put(tree,node);
					if(index==2){
						Node nodeForFirstTree= new Node(listOfTree.get(0),listOfTree.get(1));
						treeToNode.put(listOfTree.get(0),nodeForFirstTree);
					}
				}
				listOfTree.add(tree);
				index++;
			}
			System.out.println(listOfTree);
			while(listOfTree.size()!=1){
				if(listOfTree.size()%2==0 && listOfTree.size()!=6){
					for(int i=0; i<listOfTree.size();i++){
						buildSubTrees(treeToNode, listOfTree, i);
					}
					System.out.println(listOfTree);
				}else if (listOfTree.size()==6){
					for(int i=0; i<listOfTree.size()-2;i++){
						buildSubTrees(treeToNode, listOfTree, i);
					}
				}
				else{
					for(int i=0; i<listOfTree.size()-1;i++){
						buildSubTrees(treeToNode, listOfTree, i);
					}
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void buildSubTrees(HashMap<MerkleTree, Node> treeToNode,
			List<MerkleTree> listOfTree, int i) {
		MerkleTree tree=listOfTree.get(i);
		if(treeToNode.get(tree)!= null){
			// tree correspond au left tree du node
			Node node=treeToNode.get(tree);
			byte[] newHash=node.getHashOfBothTrees();
			MerkleTree newTree=new MerkleTree();
			newTree.setStartIndexOfCovering(java.lang.Math.min(tree.getStartIndexOfCovering(),node.getRightMerkleTree().getStartIndexOfCovering()));
			newTree.setEndIndexOfCovering(java.lang.Math.max(tree.getEndIndexOfCovering(),node.getRightMerkleTree().getEndIndexOfCovering()));
			newTree.setHash(newHash);
			listOfTree.remove(tree);
			listOfTree.remove(node.getRightMerkleTree());
			listOfTree.add(newTree);
			if(i==1){
				Node newNode = new Node(listOfTree.get(0),listOfTree.get(1));
				treeToNode.put(listOfTree.get(0),newNode);
			}if(i>1){
				Node newNode = new Node(listOfTree.get(i-1),newTree);
				treeToNode.put(newTree, newNode);
			}
			treeToNode.remove(tree);
			treeToNode.remove(node.getRightMerkleTree());
		}
	}

	
	//return the current root hash
	public void rootHash(){

	}

	//append event log one at a time or a batch of events at once	
	public void  appendEvent(){

	}

	//return the audit path of the i-th event
	public void genPath (int i) {

	}

	//return the consistency proof that the current tree extend the previous tree 
	public void genProof (int m){

	}


}
