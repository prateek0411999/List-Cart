package icsd;

public class ClsProduct {

	String strCatid,strProdid,strProdname,strProdDesc,strProdimg;
	int intQty;
	double dblPrice;
	

	public ClsProduct(String strCatid2,String strProdid2, String strProdname2, String strProdDesc2, String strProdimg2, int intQty2,
			double dblPrice2) {
	strCatid=strCatid2;
	strProdid=strProdid2;
	strProdname=strProdname2;
	strProdDesc=strProdDesc2;
	strProdimg=strProdimg2;
	intQty=intQty2;
	dblPrice=dblPrice2;
	}
	public String getStrCatid() {
		return strCatid;
		
	}
	public void setStrCatid(String strCatid) {
		this.strCatid = strCatid;
	}
	public String getStrProdid() {
		return strProdid;
	}
	public void setStrProdid(String strProdid) {
		this.strProdid = strProdid;
	}
	public String getStrProdname() {
		return strProdname;
	}
	public void setStrProdname(String strProdname) {
		this.strProdname = strProdname;
	}
	public String getStrProdDesc() {
		return strProdDesc;
	}
	public void setStrProdDesc(String strProdDesc) {
		this.strProdDesc = strProdDesc;
	}
	public String getStrProdimg() {
		return strProdimg;
	}
	public void setStrProdimg(String strProdimg) {
		this.strProdimg = strProdimg;
	}
	public int getIntQty() {
		return intQty;
	}
	public void setIntQty(int intQty) {
		this.intQty = intQty;
	}
	public double getDblPrice() {
		return dblPrice;
	}
	public void setDblPrice(double dblPrice) {
		this.dblPrice = dblPrice;
	}
	
	
}
