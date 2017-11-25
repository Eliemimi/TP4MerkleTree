public class MerkleTree {

	private int startIndexOfCovering;
	private int endIndexOfCovering;
	private Leaf leaf;
	private Node node;
	private byte[] hash;

	public MerkleTree() {
	}

	public MerkleTree(Leaf leaf, int startIndexOfCovering, int endIndexOfCovering, byte[] hash) {
		this.leaf = leaf;
		this.startIndexOfCovering = startIndexOfCovering;
		this.endIndexOfCovering = endIndexOfCovering;
		this.hash = hash;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Leaf getLeaf() {
		return leaf;
	}

	public void setLeaf(Leaf leaf) {
		this.leaf = leaf;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public int getStartIndexOfCovering() {
		return startIndexOfCovering;
	}

	public void setStartIndexOfCovering(int startIndexOfCovering) {
		this.startIndexOfCovering = startIndexOfCovering;
	}

	public int getEndIndexOfCovering() {
		return endIndexOfCovering;
	}

	public void setEndIndexOfCovering(int endIndexOfCovering) {
		this.endIndexOfCovering = endIndexOfCovering;
	}

}
