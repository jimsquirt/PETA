package enums;

public enum Tabs {

  STUDENT (0),
  TEACHERS (1),
  CLASS (2),
  GRADE_LEVEL (3),
  SUBJECT (4)
  ;
  
  private int index;
  
  Tabs(int i) {
    setIndex(i);
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
  
}
