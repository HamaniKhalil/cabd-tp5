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

import static HamaniKhalilTP5.SystemConfiguration.BUFFER_SIZE;
import static HamaniKhalilTP5.SystemConfiguration.THE_NONE_CHARACTER;
import static HamaniKhalilTP5.SystemConfiguration.ERROR_CODE_FULL_BLOCK;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Block {
	
	private File	file;
	
	private char	[]	tuples	= new char[BUFFER_SIZE];

	// Constructor
	public Block() {
	}
	
	public Block(File file) {
		this.file	= file;
	}
	
	// Getters
	public File getFile() {
		return file;
	}
	
	public char [] getTuples() {
		return tuples;
	}

	// Setters
	public void setFile(File file) {
		this.file = file;
	}
	
	public void setTuples(char [] tuples) {
		this.tuples	= tuples;
	}
	
	// ================================================================
	public void read() {
		try {
			FileReader		fReader	= new FileReader(file);
			BufferedReader	bReader	= new BufferedReader(fReader);
			
			String	line;
			
			int		index	= 0;
			
			while((line = bReader.readLine()) != null && index < BUFFER_SIZE) {
				tuples[index ++]	= line.charAt(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write() {
		try {
			file.delete();
			file.createNewFile();
			FileWriter		fWriter	= new FileWriter(file);
			BufferedWriter	bWriter	= new BufferedWriter(fWriter);
			
			for(int i = 0; i < tuples.length; i ++) {
				if(i != 0) {
					bWriter.append("\n");
				}
				if(tuples[i] != THE_NONE_CHARACTER) {
					bWriter.append(tuples[i]);
				}
				else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void append(char tuple) {
		if(firstEmptyIndex() != ERROR_CODE_FULL_BLOCK && tuple != THE_NONE_CHARACTER) {
			tuples[firstEmptyIndex()] = tuple;
		}
	}
	
	public int firstEmptyIndex() {
		for(int i = 0; i < tuples.length; i ++) {
			if(tuples[i] == THE_NONE_CHARACTER) {
				return i;
			}
		}
		return ERROR_CODE_FULL_BLOCK;
	}
}
