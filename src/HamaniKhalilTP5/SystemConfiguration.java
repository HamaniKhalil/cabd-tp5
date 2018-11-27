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

/*
 * This one contains the system specification parameters
 * 
 * These parameters can change through time, and they are specific to a signle configuration
 * 
 * */


package HamaniKhalilTP5;

public class SystemConfiguration {
	
	// For now, the memory buffer size is the same for disk
	public static final int		BUFFER_SIZE					= 10;
	public static final int		DESCRIPTOR_SIZE				= 30;
	public static final int		DISCRIMINATION_INDEX		= 0;
	public static final int		FIRST_ARRAY_ELEMENT_INDEX	= 0;
	public static final char	THE_NONE_CHARACTER			= '\u0000';
	
	//
	public static final String	FILENAME_SUFFIX				= ".txt"; 
	
	// Error messages
	public static final String	ERROR_BLOCK_NOT_ADDED			= "The descriptor is already full";
	public static final String	ERROR_BLOCK_REMOVE_OUTOFBOUNDS	= "The remove index is out of bounds";
	
	// Error codes
	public static final int	ERROR_CODE_FULL_BLOCK	= -101;
	
}
