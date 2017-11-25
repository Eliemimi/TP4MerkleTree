public class Node {

		private MerkleTree leftMerkleTree;
		private MerkleTree righMerkleTree;
		private byte[] hashOfBothTrees;

		public byte[] getHashOfBothTrees() {
			return hashOfBothTrees;
		}

		public void setHashOfBothTrees(byte[] hashOfBothTrees) {
			this.hashOfBothTrees = hashOfBothTrees;
		}
		
		public Node(){
			
		}

		public Node(MerkleTree leftMerkleTree, MerkleTree righMerkleTree) {
			this.setLeftMerkleTree(leftMerkleTree);
			this.setRighMerkleTree(righMerkleTree);
			byte[] hashLeftMerkleTree = leftMerkleTree.getHash();
			byte[] hashRightMerkleTree = righMerkleTree.getHash();
			hashOfBothTrees = createHashFromLeftAndRightTrees(hashLeftMerkleTree, hashRightMerkleTree, (byte) 0x01);
		}

		private byte[] createHashFromLeftAndRightTrees(
				byte[] hashLeftMerkleTree, byte[] hashRightMerkleTree,
				byte prefix) {
			byte[] concatenatedHash = concatenateTwoHashes(hashLeftMerkleTree,
					hashRightMerkleTree);
			return Util.prependAndHash(prefix, concatenatedHash);
		}

		private byte[] concatenateTwoHashes(byte[] hash1, byte[] hash2) {
			byte[] finalHash = new byte[hash1.length + hash2.length];
			System.arraycopy(hash1, 0, finalHash, 0, hash1.length);
			System.arraycopy(hash2, 0, finalHash, hash1.length, hash2.length);
			return finalHash;
		}

		public MerkleTree getLeftMerkleTree() {
			return leftMerkleTree;
		}

		public void setLeftMerkleTree(MerkleTree leftMerkleTree) {
			this.leftMerkleTree = leftMerkleTree;
		}

		public MerkleTree getRightMerkleTree() {
			return righMerkleTree;
		}

		public void setRighMerkleTree(MerkleTree righMerkleTree) {
			this.righMerkleTree = righMerkleTree;
		}
	}