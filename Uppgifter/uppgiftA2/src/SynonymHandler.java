// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
import java.util.Arrays;
// IOException

@SuppressWarnings("GrazieInspection")
class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        int numberOfLines = 0;
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			numberOfLines++;
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[numberOfLines];
        reader = new BufferedReader(new FileReader(synonymFile));
		for (int i = 0; i < numberOfLines; i++)
		    synonymData[i] = reader.readLine();
		reader.close();

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int index = synonymLineIndex(synonymData, word);

		return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data.
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
        System.arraycopy(synonymData, 0, synData, 0, synonymData.length);
		synData[synData.length - 1] = synonymLine;

	    return synData;

	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData,
	    String word) throws IllegalArgumentException
	{
		// add code here
		int index = synonymLineIndex(synonymData, word);				// Throws exception if word not found
		String[] synData = new String[synonymData.length - 1];			// Create new array with one less element
		for (int i = 0; i < index; i++)
			synData[i] = synonymData[i];								// Copy all elements before index
		for (int i = index + 1; i < synonymData.length; i++)
			synData[i-1] = synonymData[i];								// Copy all elements after index
		return synData;													// Return new array
	}

    // getSynonyms returns synonyms in a given synonym line.
	private static String[] getSynonyms (String synonymLine)
	{
        // add code here
		String[] synonyms = synonymLine.split("[|,]"); 			// Split on | and ,
		String[] result = new String[synonyms.length - 1]; 				// Create new array with one less element
		for (int i = 1; i < synonyms.length; i++) 						// Skip first element which is the word
			result[i-1] = synonyms[i].trim(); 							// Trim spaces and add to new array at i-1
																		// to skip word and start at index 0 of new array
		return result;													// Return cleaned array of synonyms
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData,
	    String word, String synonym) throws IllegalArgumentException
	{
        // add code here
		int index = synonymLineIndex(synonymData, word);				// Throws exception if word not found
		String synonymLine = synonymData[index];
		String newSynonymLine = synonymLine + ", " + synonym;
		synonymData[index] = newSynonymLine;
	}

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
    // If there is only one synonym for the given word, an
    // exception of the type IllegalStateException is thrown.
	public static void removeSynonym (String[] synonymData,
	    String word, String synonym)
	    throws IllegalArgumentException, IllegalStateException
	{
        // add code here
		int index = synonymLineIndex(synonymData, word); 							// Throws exception if word not found
		String[] synonyms = getSynonyms(synonymData[index]);
		if (synonyms.length == 1)
			throw new IllegalStateException("Only one synonym for " + word); 		// Throws exception if only one synonym
		int synonymIndex = 0;
		boolean synonymFound = false;
		while (!synonymFound && synonymIndex < synonyms.length) {
			if (synonyms[synonymIndex].equalsIgnoreCase(synonym))
				synonymFound = true;
			else
				synonymIndex++;
		}
		if (!synonymFound)
			throw new IllegalArgumentException("Synonym " + synonym +
					" not found for " + word); 										// Throws exception if synonym not found
		String newSynonymLine = word + " | ";
		for (int i = 0; i < synonyms.length; i++) {
			if (i == synonymIndex) continue;
			newSynonymLine += synonyms[i] + ", ";
		}
		newSynonymLine = newSynonymLine.substring(0, newSynonymLine.length() - 2); 	// Remove last comma and space
		synonymData[index] = newSynonymLine; 										// Replace old synonym line with new one
	}

    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
        // add code here
		for (int i = 0; i < strings.length - 1; i++) {								// Selection sort
			int minIndex = i;
			for (int j = i + 1; j < strings.length; j++) {
				if (strings[j].compareToIgnoreCase(strings[minIndex]) < 0)
					minIndex = j;
			}
			String temp = strings[i];
			strings[i] = strings[minIndex];
			strings[minIndex] = temp;
		}
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine (String synonymLine)
    {
	    // add code here
		String[] synonyms = getSynonyms(synonymLine);								// Get synonyms in an array
		sortIgnoreCase(synonyms);													// Sort the array
		String result = synonymLine.substring(0, synonymLine.indexOf('|') + 2); 	// Includes word, line and space
		for (int i = 0; i < synonyms.length; i++)
			result += synonyms[i] + ", ";											// Add sorted synonyms to result
		result = result.substring(0, result.length() - 2); 							// Remove last comma and space
		return result;																// Return sorted synonym line
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
        // add code here
		for (int i = 0; i < synonymData.length; i++)
			synonymData[i] = sortSynonymLine(synonymData[i]);						// Sort synonyms of each synonym line
		sortIgnoreCase(synonymData);												// Sort synonym lines
	}
}