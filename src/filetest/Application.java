package filetest;

import java.io.File;

import HamaniKhalilTP5.Block;
import HamaniKhalilTP5.FileManager;
import HamaniKhalilTP5.Relation;

public class Application {

	public static void main(String[] args) {
		Relation	r	= new Relation();
		Relation	s	= new Relation();
		
		FileManager	rFM	= new FileManager();
		FileManager	sFM	= new FileManager();
		
		rFM.create("R");
		sFM.create("S");
		
		r.setFileManager(rFM);
		s.setFileManager(sFM);
		
		Block	b	= new Block(new File("R/001.txt"));
		b.append('A');
		b.append('B');
		b.append('C');
		b.append('D');
		b.write();
		
		rFM.addBlock(b);
		
		b	= new Block(new File("S/001.txt"));
		b.append('A');
		b.append('B');
		b.append('C');
		b.append('D');
		b.write();
		
		sFM.addBlock(b);
		
		r.joinWithNestedLoop(s);
		
	}

}
