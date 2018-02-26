 package cn.rfatx.api.common;
 


import java.io.Serializable;
import java.util.List;


 /**
  * 保存数据的临时表
  */
 public class DataTable<T>
   implements Serializable
 {
   private static final long serialVersionUID = 2252240868205663450L;
   protected int sEcho = 1;
   protected int iDisplayStart = 0;
   protected int iDisplayLength = 20;
   protected long iTotalRecords;
   protected long iTotalDisplayRecords;
   protected List<T> aaData;
   private String sortValue;
   private String sortColName;
   private String sSearch;
   private String iSortCol_0;
   private String sSortDir_0;
   
   public int getsEcho() { return this.sEcho; }
   
   public void setsEcho(int sEcho) {
     this.sEcho = sEcho;
   }
   
   public int getiDisplayStart() { return this.iDisplayStart; }
   
 
   public void setiDisplayStart(int iDisplayStart) { this.iDisplayStart = iDisplayStart; }
   
   public int getiDisplayLength() {
     if (this.iDisplayLength == -1) {
       this.iDisplayLength = Integer.MAX_VALUE;
     }
     return this.iDisplayLength;
   }
   
   public void setiDisplayLength(int iDisplayLength) { this.iDisplayLength = iDisplayLength; }
   
   public long getiTotalRecords() {
     return this.iTotalRecords;
   }
   
   public void setiTotalRecords(long iTotalRecords) { this.iTotalRecords = iTotalRecords; }
   
   public long getiTotalDisplayRecords() {
     return this.iTotalDisplayRecords;
   }
   
   public void setiTotalDisplayRecords(long iTotalDisplayRecords) { this.iTotalDisplayRecords = iTotalDisplayRecords; }
   
   public List<T> getAaData() {
     return this.aaData;
   }
   
   public void setAaData(List<T> aaData) { this.aaData = aaData; }
   

   public String getsSearch() { return this.sSearch; }
   
   public void setsSearch(String sSearch) {
    if (sSearch != null) {
       this.sSearch = sSearch.trim();
     } else
       this.sSearch = null;
   }
   
   public String getSortColName() {
     return this.sortColName;
   }
   
   public void setSortColName(String sortColName) { this.sortColName = sortColName; }
   
   public String getSortValue() {
     return this.sortValue;
   }
   
   public void setSortValue(String sortValue) { this.sortValue = sortValue; }
   
   public String getiSortCol_0() {
     return this.iSortCol_0;
   }
   
   public void setiSortCol_0(String iSortCol_0) { this.iSortCol_0 = iSortCol_0; }
   
   public String getsSortDir_0() {
     return this.sSortDir_0;
   }
   
   public void setsSortDir_0(String sSortDir_0) { this.sSortDir_0 = sSortDir_0; }
   
   public int pageNo() {
     if (getiDisplayLength() != 0) {
       return this.iDisplayStart / getiDisplayLength();
     }
     return -1;
   }
 }
