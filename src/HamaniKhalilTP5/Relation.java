package HamaniKhalilTP5;

import static HamaniKhalilTP5.SystemConfiguration.BUFFER_SIZE;
import static HamaniKhalilTP5.SystemConfiguration.FIRST_ARRAY_ELEMENT_INDEX;
import static HamaniKhalilTP5.SystemConfiguration.THE_NONE_CHARACTER;
import static HamaniKhalilTP5.SystemConfiguration.FILENAME_SUFFIX;

import java.io.File;

import HamaniKhalilTP5.FileManager;

public class Relation {
	
	private static int	JOIN_SEQUENCE	= 0;
	
	
	public static final String	JOIN_RELATION_NAME_PREFIX	= "RS-";
	
	private FileManager	fileManager;

	// Constructors
	public Relation() {
	}
	
	public Relation(FileManager fileManager) {
		this.fileManager	= fileManager;
	}
	
	// Getters
	public FileManager getFileManager() {
		return fileManager;
	}

	// Setters
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	// ================================================================================================
	
	public void joinWithNestedLoop(Relation sRelation) {
		try {
			if(fileManager.getBlocks() != null) {
				int	rLength	= fileManager.getBlocks().length;
				if(sRelation.getFileManager().getBlocks() != null) {
					int	sLength	= fileManager.getBlocks().length;
					Relation	rsRelation	= new Relation();
					rsRelation.setFileManager(new FileManager());
					rsRelation.getFileManager().create(JOIN_RELATION_NAME_PREFIX + String.format("%03d", JOIN_SEQUENCE ++) + FILENAME_SUFFIX);
					
					int	i	= 0;
					int	j	= 0;
					
					Block	rBlock	= fileManager.getBlock(i);
					Block	sBlock	= sRelation.getFileManager().getBlock(j);
					
					while(i < rLength && rBlock != null) {
						while(j < sLength && sBlock != null) {
							char []	r	= rBlock.getTuples();
							char []	s	= sBlock.getTuples();
							
							char [] tmp	= nestedLoop(r, s);
							
							rsRelation.getFileManager().writeOnFirstBlock(tmp);
							j ++;
						}
						i ++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static char [] nestedLoop(char [] r, char [] s) {
		if(r.length > BUFFER_SIZE || s.length > BUFFER_SIZE) {
			return null;
		}

		char [] rs		= new char[BUFFER_SIZE];
		int		index	= 0;
		for(int i = FIRST_ARRAY_ELEMENT_INDEX; i < r.length; i ++) {
			for(int j = FIRST_ARRAY_ELEMENT_INDEX; j < s.length; j ++) {
				if(r[i] == s[j] && index < BUFFER_SIZE) {
					rs[index ++]	= r[i];
				}
			}
		}
		return rs[FIRST_ARRAY_ELEMENT_INDEX] == THE_NONE_CHARACTER ?
				null :
					rs;
	}
	
	
}
