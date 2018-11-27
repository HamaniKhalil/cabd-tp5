/*
 * TP N°		: 05
 * Version N°	: 01
 * 
 * Titre du TP	: File Join
 * 
 * Date			: 09 Novembre 2018
 * 
 * Nom			: Hamani
 * Prenom		: Khalil
 * N° Etudiant	: 21810826
 * 
 * Email		: hamani_khalil@yahoo.fr
 * 
 * Remarques	: N/A
 * 
 * */

package HamaniKhalilTP5;

import static HamaniKhalilTP5.SystemConfiguration.DESCRIPTOR_SIZE;
import static HamaniKhalilTP5.SystemConfiguration.ERROR_BLOCK_NOT_ADDED;
import static HamaniKhalilTP5.SystemConfiguration.ERROR_CODE_FULL_BLOCK;
import static HamaniKhalilTP5.SystemConfiguration.THE_NONE_CHARACTER;
import static HamaniKhalilTP5.SystemConfiguration.FILENAME_SUFFIX;

import java.io.File;

public class FileManager {
	
	public static final	String	PATH_SEPARATOR	= "/";
	
	private File	file;
	
	private int	countBlocks;
	
	private Block	[]	blocks	= new Block[DESCRIPTOR_SIZE];
	
	// Constructors
	public FileManager() {
	}

	// Getters
	public File getFile() {
		return file;
	}
	
	public Block[] getBlocks() {
		return blocks;
	}
	
	public Block getBlock(int index) {
		return blocks[index];
	}

	// Setters
	public void setFile(File file) {
		this.file = file;
	}

	public void setBlocks(Block[] blocks) {
		this.blocks = blocks;
	}
	
	public void setBlock(int index, Block block) {
		blocks[index] = block;
	}
	
	// ================================================================
	// Add
	public boolean addBlock(Block block) {
		boolean	added	= false;
		if(!blockExists(block)) {
			for(int i = 0; i < DESCRIPTOR_SIZE; i ++) {
				if(blocks[i] == null) {
					blocks[i]	= block;
					added	= true;
					countBlocks ++;
					break;
				}
			}
		}
		return added;
	}
	
	// Remove
	public void removeBlock(int index) throws Exception {
		if(index < 0 || index >= DESCRIPTOR_SIZE) {
			throw new Exception();
		}
		blocks[index]	= null;
		for(int i = index; i < DESCRIPTOR_SIZE - 1; i ++) {
			blocks[i]	= blocks[i + 1];
		}
		blocks[DESCRIPTOR_SIZE]	= null;
		countBlocks --;
	}
	
	public void removeBlock(Block block) throws Exception {
		for(int i = 0; i < DESCRIPTOR_SIZE; i ++) {
			if(blocks[i].equals(block)) {
				this.removeBlock(i);
			}
		}
	}
	
	// Write on first block that contains empty space
	// and create next block and writes on it while there's data
	public void writeOnFirstBlock(char [] tuples) {
		Block	block;
		for(int i = 0; i < tuples.length; i ++) {
			if(tuples[i] != THE_NONE_CHARACTER) {
				if((block	= firstEmptyBlock()) != null) {
					block.append(tuples[i]);
					if(block.firstEmptyIndex() == ERROR_CODE_FULL_BLOCK) {
						if(block.getFile() == null) {
							block.setFile(new File(file.getName() + PATH_SEPARATOR + String.format("%03d", countBlocks) + FILENAME_SUFFIX));
						}
						block.write();
					}
				}
				else {
					break;
				}
			}
			else {
				break;
			}
		}
	}
	
	public Block firstEmptyBlock() {
		for(int i = 0; i < blocks.length; i ++) {
			if(blocks[i] == null) {
				return blocks[i];
			}
		}
		return null;
	}
	
	public boolean blockExists(Block block) {
		if(blocks == null) {
			return false;
		}
		for(int i = 0; i < blocks.length; i ++) {
			if(blocks[i] != null && (blocks[i].equals(block) || blocks[i] == block)) {
				return true;
			}
		}
		return false;
	}
	
	// Create file
	public boolean create(String filename) {
		return (new File(filename)).mkdirs();
	}
}