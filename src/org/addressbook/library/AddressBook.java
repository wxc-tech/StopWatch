package org.addressbook.library;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.File;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * AddressBook is a Class which implements basic functions of an 
 * address book application including get an address book, initialize
 * an address book, add entry to it, remove entry from it, search 
 * entries by the given keyword, save the content of the address book 
 * into a file and read the content from a file into the address book
 * 
 * @author Xiaocong Wang
 */
public class AddressBook {
  private final String fieldSeparator = "  &&  ";
  private final String newLine = "\n";
  
  private static final AddressBook ADDRESSBOOK = new AddressBook();
  
  private ArrayList<Entry> entryList;
  
  private Entry entry;
  private List<Entry> searchRes;
  
  private String entryContent;
  private byte[] entryContentByte;
  
  private String[] tmpStrArray;
  private String strLine;
  
  private AddressBook() {
	  
  }
  
  /**
   *This method is invoked to get an uninitialized address book
   * @return an uninitialized address book
   */
  public static AddressBook getAddressBook() {
    return ADDRESSBOOK;
  }
  
  /**
   *This method is invoked to initialized the address book
   */
  public void initializeAddressBook() {
	entryList = new ArrayList<Entry>();
  }

  private void checkNotNull(Object object){
    if(object == null) {
	  throw new NullPointerException("The object is null!");
    }
  }
  
  private void check(boolean val) {
	if(!val) {
		throw new IndexOutOfBoundsException("The index is out of bounds!");
	}
  }
  
  /**
   *This method is invoked to add an not null entry into the address book
   * @param entry
   * @return true if add successfully, or false
   */
  public boolean addEntry(Entry entry) {
    try {
      checkNotNull(entry);
    }catch(Exception e) {
      e.printStackTrace();
    }
    return entryList.add(entry);
  }
  
  /**
   * This is method is invoked to get an entry from the address book by the valid index
   * @param index
   * @return entry with index in the address book
   */
  public Entry getEntry(int index) {
    try {
      check(index < entryList.size());
    }catch(Exception e) {
      e.printStackTrace();	
    }
    return entryList.get(index);
  }
	
 /**
  * This method is invoked to get the number of entry in the address book
  * @return number of entry
  */
  public int size() {
	try {
	  checkNotNull(entryList);
	}catch(Exception e) {
	  e.printStackTrace();
	}
	return entryList.size();
  }

  private int remove(String strInfo, int item) {
    int num = 0;
	for(Iterator<Entry> iter = entryList.iterator();iter.hasNext();) {
      entry = (Entry) iter.next();
	  if(entry.getStrInfo(item).equals(strInfo)) {
	    iter.remove();
		num++;
	  }
	}
	entry = null;
	return num;
  }
  
  /**
   * This method is invoked to remove the given not null entry from the address book 
   * @param entry
   * @return true if remove successfully or false
   */
  public boolean removeByEntry(Entry entry){
    try {
      checkNotNull(entry);
	}catch(Exception e) {
	  e.printStackTrace();
	}
	return entryList.remove(entry); 
  }
  
  /**
   * This method is invoked to remove the entry by index
   * @param index
   */
  public void removeByIndex(int index) {
    try {
	  check(index < entryList.size());
	}catch(Exception e) {
	  e.printStackTrace();
	}
    entryList.remove(index);
  }
	
  /**
   * This method is invoked to remove the entry which has the given name
   * @param name
   * @return number of entry being removed
   */
  public int removeByName(String name) {
    return remove(name, 0);
  }
	
  /**
   * This method is invoked to remove the entry which has the given postalAddress
   * @param postalAddress
   * @return number of entry being removed
   */
  public int removeByPostalAddress(String postalAddress) {
    return remove(postalAddress, 1);
  }

  /**
   * This method is invoked to remove the entry which has the given phoneNumber
   * @param phoneNumber
   * @return number of entry being removed
   */
  public int removeByPhoneNumber(String phoneNumber) {
    return remove(phoneNumber, 2);
  }
	
  /**
   * This method is invoked to remove the entry which has the given emailAddress
   * @param emailAddress
   * @return number of entry being removed
   */
  public int removeByEmailAddress(String emailAddress) {
    return remove(emailAddress, 3);
  }

  private List<Entry> search(String strInfo, int item) {
    searchRes = new ArrayList<Entry>();
	for(Iterator<Entry> iter = entryList.iterator();iter.hasNext();) {
	  Entry entry = (Entry) iter.next();
      if(entry.getStrInfo(item).equals(strInfo)) {
	    searchRes.add(entry);
	  }
	}
	return searchRes;
  }

  /**
   * This method is invoked to search the entries which have the given name
   * @param name
   * @return the List of all entries with the given name
   */
  public List<Entry> searchByName(String name) {
    return search(name, 0);
  }
	
  /**
   * This method is invoked to search the entries which have the given postalAddress
   * @param postalAddress
   * @return the List of all entries with the given postalAddress
   */
  public List<Entry> searchByPostalAddress(String postalAddress) {
    return search(postalAddress, 1);
  }
	
  /**
   * This method is invoked to search the entries which have the given phoneNumber
   * @param phoneNumber
   * @return the List of all entries with the given phoneNumber
   */
  public List<Entry> searchByPhoneNumber(String phoneNumber) {
	return search(phoneNumber, 2);
  }
	
  /**
   * This method is invoked to search the entries which have the given emailAddress
   * @param emailAddress
   * @return the List of all entries with the the given emailAddress
   */
  public List<Entry> searchByEmailAddress(String emailAddress) {
	return search(emailAddress, 3);
  }
  
  /**
   * This method is invoked to search all entries which have the String of globalKeyWord
   * @param globalKeyWord
   * @return the List of all entries which have the String of globalKeyWord
   */
  public List<Entry> searchByGlobalKeyWord(String globalKeyWord) {
    searchRes = new ArrayList<Entry>();
	for(Iterator<Entry> iter = entryList.iterator();iter.hasNext();) {
      entry = iter.next();
	  entryContent = entry.getName() + entry.getPostalAddress()
	      + entry.getPhoneNumber() + entry.getEmailAddress()
	      + entry.getNote();
	  if(entryContent.contains(globalKeyWord)) {
		  searchRes.add(entry);
	  }
	}
	return searchRes;
  }
  
  /**
   * This method is invoked to return the String representation for this address book
   * @return the String representation of an address book
   */
  @Override
  public String toString() {
    return entryList.toString();
  }
  
  /**
   * This method is invoked to save the content of an address book into the file with given file address
   * If the file with the given address is not existed, it will create this file. But the file can not be
   * a directory
   * @param address
   */
  public void saveToFile(String address) {
    File objectFile = new File(address);
    try {	
      if(!objectFile.exists()) {
        objectFile.createNewFile();
	  }else if(objectFile.isDirectory()) {
	    throw new IOException("This is a directory!");
	  }
	  OutputStream out = new FileOutputStream(objectFile, true);
	  for(Iterator<Entry> iter = entryList.iterator();iter.hasNext();) {
	    entry = (Entry) iter.next();
		entryContent = entry.getName() + fieldSeparator
		    + entry.getPostalAddress() + fieldSeparator
		    + entry.getPhoneNumber() + fieldSeparator
		    + entry.getEmailAddress() + fieldSeparator
		    + entry.getNote() + fieldSeparator + newLine;
		entryContentByte = entryContent.getBytes();
		out.write(entryContentByte);
      }
	  out.close();
    }catch(Exception e) {
      e.printStackTrace();
	}
  }

  /**
   * This method is invoked to read information from the file with the given file address into the address book
   * The file with the given address must exist, or it will throw exception. 
   * @param address
   */
  public void readFromFile(String address) {
	File objectFile = new File(address);
	try {	
      if(!objectFile.exists()) {
	    throw new FileNotFoundException("File is not existed!");
	  }
	  InputStream in = new FileInputStream(objectFile);
	  BufferedReader buf = new BufferedReader(new InputStreamReader(in));
	  while((strLine = buf.readLine()) != null) {
	    tmpStrArray = strLine.split(fieldSeparator);
		entry = new Entry.Builder(tmpStrArray[0])
				.postalAddress(tmpStrArray[1])
				.phoneNumber(tmpStrArray[2])
				.emailAddress(tmpStrArray[3])
				.note(tmpStrArray[4].substring(0,tmpStrArray[4].length()-1))
				.build();
		entryList.add(entry);
      }
	  in.close();
    }catch(Exception e) {
      e.printStackTrace();
	}
  }
}
