package org.addressbook.library;

/**
 * Entry is class which is used to store the information of an entry
 * It includes five information field:name, postalAddress, phoneNumber, emailAddress, note
 * 
 * @author Xiaocong Wang
 *
 */
class Entry {
  private final String name;
  private final String postalAddress;
  private final String phoneNumber;
  private final String emailAddress;
  private final String note;
  
  private String res = "";
  
  /**
   * Builder is a static class of class Entry which is used to construct entry object
   * 
   * @author Xiaocong Wang
   *
   */
  public static class Builder {
	private String name;
		
	private String postalAddress = "";
	private String phoneNumber = "";
	private String emailAddress = "";
	private String note = "";
		
	public Builder(String name) {
	  this.name = name;
	}
		
	public Builder postalAddress(String val) {
	  postalAddress = val;
	  return this;
	}
	
	public Builder phoneNumber(String val) {
	  phoneNumber = val;
	  return this;
	}
	
	public Builder emailAddress(String val) {
	  emailAddress = val;
	  return this;
	}
	
	public Builder note(String val) {
	  note = val;
	  return this;
	}
		
	public Entry build() {
	  return new Entry(this);
	}
  }

  /**
   * This method is constructor of Entry
   * 
   * @param builder
   */
  public Entry(Builder builder) {
    name = builder.name;
    postalAddress = builder.postalAddress;
	phoneNumber = builder.phoneNumber;
	emailAddress = builder.emailAddress;
	note = builder.note;
  }
	
  /**
   * 
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * 
   * @return postalAddress
   */
  public String getPostalAddress() {
    return postalAddress;
  }
 
  /**
   * 
   * @return phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * 
   * @return emailAddress
   */
  public String getEmailAddress() {
    return emailAddress;
  }
	
  /**
   * 
   * @return note
   */
  public String getNote() {
    return note;
  }

  /**
   * return the field of entry based on the value of item
   * @param item
   * @return String based on item
   */
  public String getStrInfo(int item) {
	switch (item) {
	  case 0: {
	    res = this.name; 
		break;
	  }
	  case 1: {
	    res = this.postalAddress;
	    break;
	  }
	  case 2: {
		res = this.phoneNumber;
	    break;
	  }
	  case 3: {
	    res = this.emailAddress;
		break;
	  }
	  default: {
	    res = this.note;
	  }
	}
	return res;
  }
	
  /**
   * return the String representation of entry
   * @return the String representation of entry
   */
  @Override
  public String toString() {
    return name + " " + postalAddress 
	    + " " + phoneNumber + " "
	    + emailAddress + " " + note;
	}
  
  /**
   * 
   * @return true if equals or false
   */
  @Override
  public boolean equals(Object object) {
	if(!(object instanceof Entry)) {
      return false;
	}
	Entry entry = (Entry) object;
	return name.equals(entry.name)
	    && postalAddress.equals(entry.postalAddress)
	    && phoneNumber.equals(entry.phoneNumber)
	    && emailAddress.equals(entry.emailAddress)
	    && note.equals(entry.note);
  }
  
  /**
   * 
   * @return the hashcode of the entry
   */
  @Override
  public int hashCode() {
    String tmp = name + postalAddress + phoneNumber
        + emailAddress + note;
    return tmp.hashCode(); 
  }
  
}
