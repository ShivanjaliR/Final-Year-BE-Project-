using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Services;
using System.Web.Services.Protocols;
using System.Xml.Linq;

namespace VirtualMoneySystem
{
    /// <summary>
    /// Summary description for VMWebService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]


    public class Profile
    {
        public String AccNo, Password, Name, Gender, Address, email, BankName, Type, BankAccNo ,DOB,ContactNo;
       
        public double MoneyPoints;

        public Profile()
        {        }
    }
    public class VendorList
    {
        public String VendorId, Name;
    }
    public class ListProducts
    {
        public Byte[] bytarray;
        public String ProductId, Name;
        public Double Price;

        public ListProducts()
        {        }
        public ListProducts(Byte[] bytearray, String PID, String N, Double P)
        {
            bytarray = bytearray;
            ProductId = PID;
            Name = N;
            Price = P;
        }
    }
    public class Product
    {
        
        public String ProductId, Name, Category, Notes, Manufacturer, VendorAcc;
        public double Price, ShippingCost, Discount, MoneyPoint;
        public int Quantity;
        public String ProcessingTime;
    }
    public class Bill
    {
        public String ProductId,Name, Vendor, Customer;
        public Double Total, Price, ShippingCost, Discount, CalculatedDiscount;        
        public int Quantity;
        public String ProcessingTime;        
    }
    public class Transaction
    {
        public String VAccNo, CAccNo, Name, Product;
        public Double AvailableMoneyPoints, Price;
        public int Quantity;
        public DateTime Date_Time;
    }
    public class Order
    {
        public String Vendor,ProductId,Customer,Delivered;
        public Double Total;
        public int Quantity;
        public String ProcessingTime;        
    }
    public class VMWebService : System.Web.Services.WebService
    {
        public static SqlConnection conn = new SqlConnection("Data Source=SUAJIT;Initial Catalog=master;Integrated Security=True");       
        Product P = new Product();
        Profile PF = new Profile();

        //--------------------------------------------------------------------------------------------------------
        // CUSTOMER   AND  VENDOR
        //--------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Boolean DoRegistration(String sAccNo, String sPassword, String sName, String sGender, String sAddress, String sEmail, String sBankName, String sUserType, String sContactNo, String sBankAccNo, String sDOB)
        {
            //Todo: Need to implement code for validation and creating new account on server database
            try
            {
                //check that the username doesn't already exist
                if (IsUserExists(sAccNo))
                {
                    return false;
                }
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
                conn.Open();
                SqlCommand comm = new SqlCommand("INSERT INTO Profile (AccNo, Password, Name, Gender, DOB, ContactNo, Addr, Type, Email, BankName, BankAccNo,MoneyPoints) VALUES (@AccNo, @Password, @Name, @Gender, @DOB, @ContactNo, @Address, @Type, @email, @BankName, @BankAccNo,@MoneyPoints)", conn);
                comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                comm.Parameters.Add(new SqlParameter("@Password", sPassword));
                comm.Parameters.Add(new SqlParameter("@Name", sName));
                comm.Parameters.Add(new SqlParameter("@Gender", sGender));
                comm.Parameters.Add(new SqlParameter("@DOB", sDOB));
                comm.Parameters.Add(new SqlParameter("@ContactNo", sContactNo));
                comm.Parameters.Add(new SqlParameter("@Address", sAddress));
                comm.Parameters.Add(new SqlParameter("@Type", sUserType));
                comm.Parameters.Add(new SqlParameter("@email", sEmail));
                comm.Parameters.Add(new SqlParameter("@BankName", sBankName));
                comm.Parameters.Add(new SqlParameter("@BankAccNo", sBankAccNo));
                comm.Parameters.Add(new SqlParameter("@MoneyPoints","0"));

                SqlDataReader r = comm.ExecuteReader();
                comm.Dispose();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.ToString(), ex);
            }
            finally
            {
                conn.Close();
            }
            return true;
        }
        private bool IsUserExists(String sAccNo)
        {
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("select Name from Profile where AccNo=@AccNo", conn);
            comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
            SqlDataReader r = comm.ExecuteReader();
            bool result = r.HasRows;
            conn.Close();
            return result;
        }
        //--------------------------------------------------------------------------------------------------------
        //CUSTOMER    AND   VENDOR
        //--------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Boolean DoLogin(String sUserName, String sPassword,String sUserType)
        {
            //Todo: Need to implement code for validating username and password    
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("select * from Profile where AccNo = @sUserName AND Password = @sPassword AND Type = @sUserType", conn);
            comm.Parameters.Add(new SqlParameter("@sUserName", sUserName));
            comm.Parameters.Add(new SqlParameter("@sPassword", sPassword));
            comm.Parameters.Add(new SqlParameter("@sUserType", sUserType));
            SqlDataReader r = comm.ExecuteReader();
            bool result = r.HasRows;
            conn.Close();

            if (result)
            {
                return true;
            }
            return false;
        }
        //--------------------------------------------------------------------------------------------------------
        //CUSTOMER & VENDOR
        //---------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Profile DisplayUserProfile(String sAccNo, String sUserType)
        {
            //Todo: Need to implement code for displaying and updating product details
            Profile P = new Profile();
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("select * from Profile where AccNo= @sAccNo AND Type=@sUserType", conn);
            comm.Parameters.Add(new SqlParameter("@sAccNo", sAccNo));
            comm.Parameters.Add(new SqlParameter("@sUserType", sUserType));
            SqlDataReader r = comm.ExecuteReader();

            bool result = r.HasRows;

            while (r.Read())
            {
                P.AccNo = r.GetString(0);
                P.Name = r.GetString(2);
                P.Gender = r.GetString(3);
                P.DOB = r.GetString(4);
                P.ContactNo = r.GetString(5);
                P.Address = r.GetString(6);
                P.Type = r.GetString(7);
                P.email = r.GetString(8);
                P.BankName= r.GetString(9);             
                P.BankAccNo = r.GetString(10);
                P.MoneyPoints = (Double)r[11]; ;
                conn.Close();
                return P;
            }
            conn.Close();
            return null;
        }
        //--------------------------------------------------------------------------------------------------------
        //VENDOR
        //---------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Boolean UpdateProfile(String sAccNo,String sName, String sGender, String sAddress, String sEmail, String sBankName, String sContactNo, String sBankAccNo, String sDOB)
        {
            //Todo: Need to implement code for validation and creating new account on server database
            try
            {
                //check that the username doesn't already exist         
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
                conn.Open();
                SqlCommand comm = new SqlCommand("UPDATE Profile SET  Name=@Name, Gender= @Gender, DOB= @DOB, ContactNo= @ContactNo, Addr= @Address, Email=@email, BankName= @BankName, BankAccNo=@BankAccNo where AccNo=@AccNo", conn);
                comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));                
                comm.Parameters.Add(new SqlParameter("@Name", sName));
                comm.Parameters.Add(new SqlParameter("@Gender", sGender));
                comm.Parameters.Add(new SqlParameter("@DOB", sDOB));
                comm.Parameters.Add(new SqlParameter("@ContactNo", sContactNo));
                comm.Parameters.Add(new SqlParameter("@Address", sAddress));                
                comm.Parameters.Add(new SqlParameter("@email", sEmail));
                comm.Parameters.Add(new SqlParameter("@BankName", sBankName));
                comm.Parameters.Add(new SqlParameter("@BankAccNo", sBankAccNo));            

                SqlDataReader r = comm.ExecuteReader();
                comm.Dispose();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.ToString(), ex);
            }
            finally
            {
                conn.Close();
            }
            return true;
        }
        //--------------------------------------------------------------------------------------------------------
          //VENDOR
        //---------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Boolean AddProduct(String sProductId, String sProductName, int iQuantity, Double dPrice, String sCategory,  String sManufacturer, Double dDiscount,String sNotes, Double dShippingCost, String sProcessingTime, String sVendorAccNo)
        {
            //Todo: Need to implement code to add products
            try
            {
                //check that the username doesn't already exist
                if (ProductExists(sVendorAccNo, sProductId))
                {
                    return false;
                }
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
                conn.Open();
                SqlCommand comm = new SqlCommand("insert into Product values (@ProductID, @Name, @Quantity, @Price, @Category, @Notes, @Manufacturer, @Discount, @ShippingCost, @ProcessingTime,@VendorAcc)", conn);

                comm.Parameters.Add(new SqlParameter("@ProductID", sProductId));
                comm.Parameters.Add(new SqlParameter("@Name", sProductName));
                comm.Parameters.Add(new SqlParameter("@Quantity", iQuantity.ToString()));
                comm.Parameters.Add(new SqlParameter("@Price", dPrice.ToString()));
                comm.Parameters.Add(new SqlParameter("@Category", sCategory));
                comm.Parameters.Add(new SqlParameter("@Notes", sNotes));
                comm.Parameters.Add(new SqlParameter("@Manufacturer", sManufacturer));
                comm.Parameters.Add(new SqlParameter("@Discount", dDiscount.ToString()));
                comm.Parameters.Add(new SqlParameter("@ShippingCost", dShippingCost.ToString()));
                comm.Parameters.Add(new SqlParameter("@ProcessingTime", sProcessingTime));//comm.Parameters.Add(new SqlParameter("@ProcessingTime", ProcessingTime.Hour));
                comm.Parameters.Add(new SqlParameter("@VendorAcc", sVendorAccNo));

                SqlDataReader r = comm.ExecuteReader();
                comm.Dispose();
                conn.Close();
                return true;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.ToString(), ex);
            }
        }
        //----------------------To check whether product exist or not--------------------------------
        private bool ProductExists(String VendorAcc, String ProductId)
        {
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("select Name from Product where  VendorAcc= @VendorAcc AND ProductId=@ProductId", conn);
            comm.Parameters.Add(new SqlParameter("@VendorAcc", VendorAcc));
            comm.Parameters.Add(new SqlParameter("@ProductId", ProductId));
            SqlDataReader r = comm.ExecuteReader();
            bool result = r.HasRows;
            conn.Close();
            return result;
        }
        //--------------------------------------------------------------------------------------------------------
        //VENDOR
        //--------------------------------------------------------------------------------------------------------
       [WebMethod]
        public String DisplayProductlist(String sVendorAccNo)
        {
            //todo: need to implement code for displaying product list            
            int i = 1, j = 0;
            String s = "", sPID = "", sPrd = "", sPrice = "";
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            //cursor required
            SqlCommand comm = new SqlCommand("cursorforlistofproduct", conn);
            comm.CommandType = CommandType.StoredProcedure;            
            comm.Parameters.Add("@sVendorAccNo", sVendorAccNo);

            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);

            // now, we get data from each row in the table (like a cursor)
            s += "<ListProduct>";
            sPID += "<ProductID>";
            sPrd += "<Name>";
            sPrice += "<Price>";
            
            foreach (DataRow row in table.Rows)
            {
                ListProducts LP = new ListProducts();
                if (i == 1)
                {
                    j = 0;
                    LP.ProductId = row[j].ToString().Trim();
                    j++;
                    LP.Name = row[j].ToString().Trim();
                    j++;
                    LP.Price = (double)row[j];                    
                    sPID += LP.ProductId;
                    sPrd += LP.Name;
                    sPrice += LP.Price;
                }
                else
                {
                    j = 0;
                    LP.ProductId = row[j].ToString().Trim();
                    j++;
                    LP.Name = row[j].ToString().Trim();
                    j++;
                    LP.Price = (double)row[j];
                    //s += "<ListProduct><ProductID>" + LP.ProductId + "</ProductID><Name>" + LP.Name + "</Name><Price>" + LP.Price + "</Price></ListProducts>";
                    sPID +=";"+ LP.ProductId;
                    sPrd += ";"+LP.Name ;
                    sPrice += ";"+LP.Price;
                }
                i++;
            }
            sPID += "</ProductID>";
            sPrd += "</Name>";
            sPrice += "</Price>";
            s += sPID.Trim() + sPrd.Trim() + sPrice.Trim() +"</ListProduct>";
            s.Trim();
            
            conn.Close();
            return s;
        }  
        //--------------------------------------------------------------------------------------------------------
        //VENDOR
        //--------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Boolean UpadateProductDetails(String sProductId, String sProductName, int iQuantity, Double dPrice, String sCategory, String sManufacturer, Double dDiscount, String sNotes, Double dShippingCost, String sProcessingTime, String sVendorAccNo)
        {
            //Todo: Need to implement code for updating product details            
            try
            {
                //if (ProductExists(VendorAcc, ProductId))
                //{
                //    return false;
                //}
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
                conn.Open();
                SqlCommand comm = new SqlCommand("update Product set ProductID=@ProductID,Name=@Name,Quantity=@Quantity,Price=@Price,Category=@Category,Notes=@Notes,Manufacturer=@Manufacturer, Discount=@Discount, ShippingCost=@ShippingCost,ProcessingTime=@ProcessingTime,VendorAcc=@VendorAcc where(VendorAcc=@VendorAcc AND ProductID=@ProductID)", conn);

                comm.Parameters.Add(new SqlParameter("@ProductID", sProductId));
                comm.Parameters.Add(new SqlParameter("@Name", sProductName));
                comm.Parameters.Add(new SqlParameter("@Quantity", iQuantity.ToString()));
                comm.Parameters.Add(new SqlParameter("@Price", dPrice.ToString()));
                comm.Parameters.Add(new SqlParameter("@Category", sCategory));
                comm.Parameters.Add(new SqlParameter("@Notes", sNotes));
                comm.Parameters.Add(new SqlParameter("@Manufacturer", sManufacturer));
                comm.Parameters.Add(new SqlParameter("@Discount", dDiscount.ToString()));
                comm.Parameters.Add(new SqlParameter("@ShippingCost", dShippingCost.ToString()));
                comm.Parameters.Add(new SqlParameter("@ProcessingTime", sProcessingTime));
                comm.Parameters.Add(new SqlParameter("@VendorAcc", sVendorAccNo));

                SqlDataReader r = comm.ExecuteReader();
                comm.Dispose();
                conn.Close();
                return true;
            }

            catch (Exception ex)
            {
                throw new Exception(ex.ToString(), ex);
            }
        }
        //--------------------------------------------------------------------------------------------------------
                     //VENDOR
        //--------------------------------------------------------------------------------------------------------
        [WebMethod]
        public String ViewVendorTransaction(String sAccNo)
        {
            //Todo:Need to implement code to show Logs to VENDOR
            int i = 1, j = 0;
            String logs = "", SenderAcc = "", ReceiverAcc = "", Date_Time = "",SAcc,oDate_Time,RecAcc;
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            //cursor required
            SqlCommand comm = new SqlCommand("select * from Logs Where SenderAcc=@sAccNo", conn);
            comm.Parameters.Add(new SqlParameter("@sAccNo", sAccNo));
            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);
            logs = "<Log>";
            SenderAcc="<SenderAcc>";
            ReceiverAcc="<ReceiverAcc>";
            Date_Time="<Date_Time>";
            // now, we get data from each row in the table (like a cursor)
            foreach (DataRow row in table.Rows)
            {
                 j = 0;                             
                SAcc = row[j].ToString();   
                j++;
                oDate_Time = row[j].ToString();             
                j++;
                RecAcc = row[j].ToString();
                if (i == 1)
                {
                    SenderAcc += SAcc;
                    Date_Time += oDate_Time;
                    ReceiverAcc += RecAcc;
                    i++;
                }
                else
                {
                    SenderAcc += ";" + SAcc;
                    Date_Time += ";" + oDate_Time;
                    ReceiverAcc += ";" + RecAcc;
                }
            }
            conn.Close();
            SenderAcc += "</SenderAcc>";
            ReceiverAcc += "</ReceiverAcc>";
            Date_Time += "</Date_Time>";

            logs += SenderAcc + Date_Time + ReceiverAcc + "</Log>";
            logs.Trim();
            return logs;
        }
        [WebMethod]
        public Boolean DeleteProduct(String sVendorAccNo, String sProductId)
        {
            //Todo: Need to implement code for validating username and password
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("Delete from Product where VendorAcc= @VendorAcc AND ProductId = @ProductId", conn);
            comm.Parameters.Add(new SqlParameter("@VendorAcc", sVendorAccNo));
            comm.Parameters.Add(new SqlParameter("@ProductId", sProductId));

            SqlDataReader r = comm.ExecuteReader();
            bool result = r.HasRows;
            conn.Close();

            if (result)
            {
                return true;
            }
            return false;
        }
        //--------------------------------------------------------------------------------------------------------
        [WebMethod]
        public String DispVendorList()
        {
            //Todo: Need to implement code for displaying vendor list
            //NEEDS CURSOR
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            string s = "", sVName = "", sVId="";
            //cursor required
            SqlCommand comm = new SqlCommand("Select AccNo,Name from Profile where Type='VENDOR' ", conn);
            //comm.CommandType = CommandType.StoredProcedure;

            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);

            int i=1,j = 0;
            ListProducts LP = new ListProducts();
            // now, we get data from each row in the table (like a cursor)
            s="<VendorList>";
            sVName = "<Name>";
            sVId = "<VendorId>";
            foreach (DataRow row in table.Rows)
            {
                j = 0;
                PF.AccNo=row[j].ToString().Trim();
                j++;
                PF.Name = row[j].ToString().Trim();                
                if (i == 1)
                {
                    sVName += PF.Name;
                    sVId += PF.AccNo;
                    i++;
                }
                else
                {
                    sVId+=";"+PF.AccNo;
                    sVName += ";" + PF.Name;
                }                
            }
            conn.Close();
            sVName += "</Name>";
            sVId += "</VendorId>";
            s += sVId + sVName + "</VendorList>"; 
           
            return s;           
        }
        [WebMethod]
        public String DispCustomerList()
        {
            //Todo: Need to implement code for displaying customer list
            //NEED CURSOR
            string sName = null, sAddr = null;
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            string s = null,Name,Address;
            //cursor required
            SqlCommand comm = new SqlCommand("Select Name, Addr from Profile where Type='CUSTOMER' ", conn);

            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);

            int i = 1, j = 0;

            s += "<ListCustomer>";
            sName += "<Name>";
            sAddr += "<Address>";
            // now, we get data from each row in the table (like a cursor)            
            foreach (DataRow row in table.Rows)
            {
                j = 0;
                Name = row[j++].ToString();
                Address = row[j++].ToString();
                if (i == 1)
                {
                    sName += Name.Trim();
                    sAddr += Address.Trim();
                    i++;
                }
                else
                {
                    sName += ";" + Name.Trim();
                    sAddr += ";" + Address.Trim();
                }
            }
            sName += "</Name>";
            sAddr += "</Address>";
            s += sName.Trim() + sAddr.Trim() + "</ListCustomer>";
            conn.Close();
            return s;
        }

        [WebMethod]
        public String DisplayTrasactionDetails(String sSenderAccNo)
        {
            //Todo:Need to implement code to show Logs to Customer/Vendor

            int i = 0, j = 0;
            String logs = "", SendorAccNo = "", ReceiAccNo = "", Date_Time = "", Type = "", Amt = "";
            String AccNo = "", Date_t = "", RecAccNo = "", type = "", amnt = "";
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            //cursor required
            SqlCommand comm = new SqlCommand("select * from Logs where SenderAcc=@SAccNo OR ReceiverAcc=@SAccNo order by Date_Time desc", conn);
            comm.Parameters.Add(new SqlParameter("@SAccNo", sSenderAccNo));
            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);
            SendorAccNo += "<SendorAccNo>";
            ReceiAccNo += "<ReceiAccNo>";
            Date_Time += "<Date_Time>";
            Type += "<Type>";
            Amt += "<Amount>";
            logs += "<Logs>";
            // now, we get data from each row in the table (like a cursor)
            foreach (DataRow row in table.Rows)
            {
                if (i == 0)
                {
                    j = 0;
                    AccNo = row[j].ToString(); j++;
                    Date_t = row[j].ToString(); j++;
                    RecAccNo = row[j].ToString(); j++;
                    type = row[j].ToString(); j++;
                    amnt = row[j].ToString();

                    SendorAccNo += AccNo.Trim();
                    ReceiAccNo += RecAccNo.Trim();
                    Date_Time += Date_t.Trim();
                    Type += type.Trim();
                    Amt += amnt.Trim();
                    i++;
                }
                else
                {
                    j = 0;
                    AccNo = row[j].ToString(); j++;
                    Date_t = row[j].ToString(); j++;
                    RecAccNo = row[j].ToString(); j++;
                    type = row[j].ToString(); j++;
                    amnt = row[j].ToString();

                    SendorAccNo += ";" + AccNo.Trim();
                    ReceiAccNo += ";" + RecAccNo.Trim();
                    Date_Time += ";" + Date_t.Trim();
                    Type += ";" + type.Trim();
                    Amt += ";" + amnt.Trim();

                }
            }
            comm.Dispose();
            conn.Close();

            SendorAccNo += "</SendorAccNo>";
            ReceiAccNo += "</ReceiAccNo>";
            Date_Time += "</Date_Time>";
            Type += "</Type>";
            Amt += "</Amount>";
            logs += SendorAccNo + ReceiAccNo + Date_Time + Type + Amt + "</Logs>";
            conn.Close();
            return logs;
        }
        
        
        //-----------------------------------------------------------------------------------------------------
        [WebMethod]
        public Bill Buy(String sVendorAccNo, String sProductId, String sCustAccNo, int iQuantity)
        {
            //Todo: Need to implement code for billing
            Bill oBill = new Bill();
            oBill.Total = 0;
            oBill.Price = 0;
            //----------------------If ip Quantity is Zero
            if (iQuantity == 0)
            { return oBill; }
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            //---------------------- Fetch price of product
            SqlCommand comm = new SqlCommand("Select Name, Quantity, Price, Discount, ShippingCost,ProcessingTime,ProductId from Product where  ProductID = @ProdId and VendorAcc = @VendorAcc", conn);
            comm.Parameters.Add(new SqlParameter("@ProdId", sProductId));
            comm.Parameters.Add(new SqlParameter("@VendorAcc", sVendorAccNo));
            SqlDataReader r = comm.ExecuteReader();
            while (r.Read())
            {
                P.Name = r.GetString(0);
                P.Quantity = (int)r[1];
                P.Price = r.GetDouble(2);
                P.Discount = oBill.Discount = r.GetDouble(3);
                P.ShippingCost = oBill.ShippingCost = r.GetDouble(4);
                P.ProcessingTime = oBill.ProcessingTime = r.GetString(5);
                oBill.ProductId = r.GetString(6);
            }
            comm.Dispose();
            conn.Close();
            if (P.Quantity < iQuantity)//If Quantity is not enough then can not do transaction
            {
                return oBill;
            }

            P.Discount = (P.Discount / 100) * P.Price;
            oBill.CalculatedDiscount = P.Discount;
            oBill.Total = P.Price - P.Discount;
            oBill.Total = (oBill.Total * iQuantity) + P.ShippingCost;


            //-------------------- Fetch money points of product
            conn.Open();
            comm = new SqlCommand("Select MoneyPoints from Profile where AccNo = @CustAcc", conn);
            comm.Parameters.Add(new SqlParameter("@CustAcc", sCustAccNo));
            r = comm.ExecuteReader();
            while (r.Read())
            {
                PF.MoneyPoints = r.GetDouble(0);
            }
            comm.Dispose();
            conn.Close();

            if (PF.MoneyPoints < oBill.Total)//If Customer doesn't have sufficient balance then CAN NOT buy product
            {
                oBill.Total = 0;
                return oBill;
            }
            else
            {
                //-------------------- Update Customer's Account
                Double temp;
                temp = PF.MoneyPoints - oBill.Total;
                conn.Open();
                comm = new SqlCommand("Update Profile set MoneyPoints = @temp where  AccNo = @SenderAcc", conn);
                comm.Parameters.Add(new SqlParameter("@SenderAcc", sCustAccNo));
                comm.Parameters.Add(new SqlParameter("@temp", temp));
                r = comm.ExecuteReader();
                conn.Close();

                //-------------------- Fetch money points of Vendor
                conn.Open();
                comm = new SqlCommand("Select MoneyPoints from Profile where AccNo = @sVendorAcc", conn);
                comm.Parameters.Add(new SqlParameter("@sVendorAcc", sVendorAccNo));
                r = comm.ExecuteReader();
                while (r.Read())
                {
                    PF.MoneyPoints = r.GetDouble(0);
                }
                conn.Close();

                //---------------------- Update Vendor's Account
                PF.MoneyPoints = PF.MoneyPoints + oBill.Total;
                temp = PF.MoneyPoints;
                conn.Open();
                comm = new SqlCommand("Update Profile set MoneyPoints = @temp where  AccNo = @RecAcc", conn);
                comm.Parameters.Add(new SqlParameter("@RecAcc", sVendorAccNo));
                comm.Parameters.Add(new SqlParameter("@temp", temp));
                r = comm.ExecuteReader();
                conn.Close();

                //---------------------- Update Product Status
                P.Quantity = P.Quantity - iQuantity;
                int quant = P.Quantity;
                conn.Open();
                comm = new SqlCommand("Update Product set Quantity = @quant where  ProductID = @sProdId", conn);
                comm.Parameters.Add(new SqlParameter("@sProdId", sProductId));
                comm.Parameters.Add(new SqlParameter("@quant", quant));
                r = comm.ExecuteReader();
                conn.Close();

                //---------------------- Insert Logs

                DateTime sDate = DateTime.Now;
                String sType = "Product - " + P.Name;
                String sAmt = oBill.Total.ToString();
                conn.Open();
                comm = new SqlCommand("INSERT INTO Logs (SenderAcc, Date_Time, ReceiverAcc, Type,  Amount) VALUES (@CustAccNo, @Date, @VendorAcc, @type, @amt)", conn);
                comm.Parameters.Add(new SqlParameter("@CustAccNo", sCustAccNo));
                comm.Parameters.Add(new SqlParameter("@Date", sDate));
                comm.Parameters.Add(new SqlParameter("@type", sType));
                comm.Parameters.Add(new SqlParameter("@amt", sAmt));
                comm.Parameters.Add(new SqlParameter("@VendorAcc", sVendorAccNo));
                r = comm.ExecuteReader();
                comm.Dispose();
                conn.Close();


                oBill.Name = P.Name;
                oBill.Price = P.Price;
                oBill.Quantity = iQuantity;
                oBill.Vendor = sVendorAccNo;
                oBill.Customer = sCustAccNo;

                /*//---------------------- Insert Order in order table
                
                conn.Open();
                comm = new SqlCommand("SELECT * FROM Order where ", conn);
                comm.Parameters.Add(new SqlParameter("@SrNo", sSrNo));
                comm.Parameters.Add(new SqlParameter("@C_P", sC_P));
                comm.Parameters.Add(new SqlParameter("@shippingTime", oBill.ProcessingTime));
                comm.Parameters.Add(new SqlParameter("@Quantity", oBill.Quantity));

                comm.Dispose();
                conn.Close();


                conn.Open();
                comm = new SqlCommand("INSERT INTO Order (SrNo, Cust_Prod, Address, Quantity, ShippingTime) VALUES (@SrNo,@sC_P,@CustAccNo, @sDate, @shippingTime, @Quantity, @amt,@Delivered)", conn);
                comm.Parameters.Add(new SqlParameter("@SrNo", sSrNo));
                comm.Parameters.Add(new SqlParameter("@C_P", sC_P));
                comm.Parameters.Add(new SqlParameter("@shippingTime", oBill.ProcessingTime));
                comm.Parameters.Add(new SqlParameter("@Quantity", oBill.Quantity));

                comm.Dispose();
                conn.Close();*/

                return oBill;
            }
        }
        //----------------------------------------------------------------------------------------------------
        //  Vendor
        //----------------------------------------------------------------------------------------------------
        /*[WebMethod]
        public Order DisplayOrderDue(String sVendorAccNo)
        {
            //Todo: Need to implement code for displaying and updating product details
            Order Od = new Order();
            conn.Open();
            SqlCommand comm = new SqlCommand("select * from Order where VendorAcc= @VendorAcc", conn);
            comm.Parameters.Add(new SqlParameter("@VendorAcc", sVendorAccNo));
            SqlDataReader r = comm.ExecuteReader();
            while (r.Read())
            {
                Od.Vendor = r.GetString(0);
                Od.ProductId = r.GetString(1);
                Od.Customer = r.GetString(2);
                Od.OrderTime = r.GetString(3);
                Od.ProcessingTime = r.GetDouble(4);
                Od.Quantity = r.GetDouble(5);
                Od.Total = r.GetDouble(6);
                Od.Delivered = r.GetString(7);

            }
            conn.Close();
            return Od;
        }
        [WebMethod]
        public Boolean DeliveredProduct(String sVendorAccNo,String sCustomerAcc,String sPrdId,)
        {
            //Todo: Need to implement code for displaying and updating product details
            Order Od = new Order();
            conn.Open();
            SqlCommand comm = new SqlCommand("delete from Order where VendorAcc= @VendorAcc And ProductID=@Pid And CustomerAccNo=@cust", conn);
            comm.Parameters.Add(new SqlParameter("@VendorAcc", sVendorAccNo));
            SqlDataReader r = comm.ExecuteReader();
            while (r.Read())
            {
                Od.Vendor = r.GetString(0);
                Od.ProductId = r.GetString(1);
                Od.Customer = r.GetString(2);
                Od.OrderTime = r.GetString(3);
                Od.ProcessingTime = r.GetDouble(4);
                Od.Quantity = r.GetDouble(5);
                Od.Total = r.GetDouble(6);
                Od.Delivered = r.GetString(7);

            }
            conn.Close();
        }*/
        //----------------------------------------------------------------------------------------------------
        //  Vendor and Customer
        //----------------------------------------------------------------------------------------------------

        [WebMethod]
        public Boolean ShareMoneyPoints(String sSenderAccNo, String sRecAccNo, double dMoneyPoints)
        {
            //Todo: Need to implement code for sharing money points
            double temp;
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            // Fetch money points from sender
            SqlCommand comm = new SqlCommand("Select * from Profile where  AccNo = @SenderAcc", conn);
            comm.Parameters.Add(new SqlParameter("@SenderAcc", sSenderAccNo));
            SqlDataReader r = comm.ExecuteReader();
            while (r.Read())
            {
                PF.MoneyPoints = r.GetDouble(11);
            }

            comm.Dispose();
            conn.Close();

            if (PF.MoneyPoints >= dMoneyPoints)
            {
                temp = PF.MoneyPoints - dMoneyPoints;
            }
            else
            {
                return false;
            }

            // Update money points in sender's account
            conn.Open();
            comm = new SqlCommand("Update Profile set MoneyPoints = @temp where  AccNo = @SenderAcc", conn);
            comm.Parameters.Add(new SqlParameter("@SenderAcc", sSenderAccNo));
            comm.Parameters.Add(new SqlParameter("@temp", temp));
            r = comm.ExecuteReader();
            conn.Close();

            // Fetch money points from receiver
            conn.Open();
            comm = new SqlCommand("Select * from Profile where  AccNo = @RecAcc", conn);
            comm.Parameters.Add(new SqlParameter("@RecAcc", sRecAccNo));
            r = comm.ExecuteReader();
            while (r.Read())
            {
                PF.MoneyPoints = r.GetDouble(11);
            }
            temp = PF.MoneyPoints + dMoneyPoints;
            conn.Close();

            // Update money points in receiver's account
            conn.Open();
            comm = new SqlCommand("Update Profile set MoneyPoints = @temp where  AccNo = @RecAcc", conn);
            comm.Parameters.Add(new SqlParameter("@temp", temp));
            comm.Parameters.Add(new SqlParameter("@RecAcc", sRecAccNo));
            r = comm.ExecuteReader();

            conn.Close();
            
            //---------------------- Insert Logs

            DateTime sDate = DateTime.Now;
            String sType = "Share Money Points";
            conn.Open();
            comm = new SqlCommand("INSERT INTO Logs (SenderAcc, Date_Time, ReceiverAcc, Type,  Amount) VALUES (@CustAccNo, @Date, @VendorAcc, @type, @amt)", conn);
            comm.Parameters.Add(new SqlParameter("@CustAccNo", sSenderAccNo));
            comm.Parameters.Add(new SqlParameter("@Date", sDate));
            comm.Parameters.Add(new SqlParameter("@type", sType));
            comm.Parameters.Add(new SqlParameter("@amt", dMoneyPoints));
            comm.Parameters.Add(new SqlParameter("@VendorAcc", sRecAccNo));
            r = comm.ExecuteReader();
            comm.Dispose();
            conn.Close();
            return true;
        }
        //--------------------------------------------------------------------------------------------------------
        //CUSTOMER    AND   VENDOR
        //--------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Product DisplayProductDetail(String sVendorAccNo, String sProductId, String sAccNo)
        {
            //Todo: Need to implement code for displaying and updating product details
            Product P = new Product();
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("select * from Product where VendorAcc= @VendorAcc AND ProductId=@ProductId", conn);
            comm.Parameters.Add(new SqlParameter("@VendorAcc", sVendorAccNo));
            comm.Parameters.Add(new SqlParameter("@ProductId", sProductId));
            SqlDataReader r = comm.ExecuteReader();
            while (r.Read())
            {
                P.ProductId = sProductId;
                P.Name = r.GetString(1);
                P.Quantity = (int)r[2];
                P.Price = r.GetDouble(3);
                P.Category = r.GetString(4);
                P.Notes = r.GetString(5);
                P.Manufacturer = r.GetString(6);
                P.Discount = r.GetDouble(7);
                P.ShippingCost = r.GetDouble(8);
                //P.ProcessingTime = r.GetDateTime(9);
                P.ProcessingTime = r.GetString(9);
                P.VendorAcc = sVendorAccNo;
                
            }
            conn.Close();

            conn.Open();
            comm = new SqlCommand("select MoneyPoints from Profile where AccNo= @AccNo", conn);
            comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));           
            r = comm.ExecuteReader();
            while (r.Read())
            {
                P.MoneyPoint = r.GetDouble(0);
                conn.Close();
                return P;
            }
            conn.Close();
            return null;
        }
        //------------------------------------------------------------------------------------------------------------
        //Administrator
        //----------------------------------------------------------------------------------------------------------
        [WebMethod]
        public String DispMemberList()
        {
            //Todo: Need to implement code for displaying vendor list
            //NEED CURSOR
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            string s = "", sVName = "", sVId = "";
            //cursor required
            SqlCommand comm = new SqlCommand("Select AccNo,Name from Profile where Type <> 'Admin'", conn);
            //comm.CommandType = CommandType.StoredProcedure;

            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);

            int i = 1, j = 0;
            ListProducts LP = new ListProducts();

            // now, we get data from each row in the table (like a cursor)
            s = "<MemberList>";
            sVName = "<Name>";
            sVId = "<MemberId>";
            foreach (DataRow row in table.Rows)
            {
                j = 0;
                PF.AccNo = row[j].ToString().Trim();
                j++;
                PF.Name = row[j].ToString().Trim();
                if (i == 1)
                {
                    sVName += PF.Name;
                    sVId += PF.AccNo;
                    i++;
                }
                else
                {
                    sVId += ";" + PF.AccNo;
                    sVName += ";" + PF.Name;
                }
            }
            conn.Close();
            sVName += "</Name>";
            sVId += "</MemberId>";
            s += sVId + sVName + "</MemberList>";

            return s;
        }
        [WebMethod]
        public Boolean SendQuery(String sSenderAccNo, String sQuery,String sQuerySubject)
        {
            //Todo: To insert query to Query table
            DateTime sDate = DateTime.Now;
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            try
            {
                SqlCommand comm = new SqlCommand("insert into Query values (@SenderAcc,@QSub,@Query,null,@SendingDate,null)", conn);
                comm.Parameters.Add(new SqlParameter("@SenderAcc", sSenderAccNo));
                comm.Parameters.Add(new SqlParameter("@QSub", sQuerySubject));
                comm.Parameters.Add(new SqlParameter("@Query", sQuery));
                comm.Parameters.Add(new SqlParameter("@SendingDate", sDate));

                SqlDataReader r = comm.ExecuteReader();
                comm.Dispose();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.ToString(), ex);
            }
            finally
            {
                conn.Close();
            }
            return true;
        }
        //------------------------------------------------------------------------------------------------------------
        //Administrator
        //----------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Boolean ReplyToQuery(String sSenderAccNo, String sReply,String sSubject)
        {
            //Todo:Need to implement code to show Query to ADMINISTRATOR
            try
            {
                DateTime sDate = DateTime.Now;
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
                conn.Open();
                SqlCommand comm = new SqlCommand("update Query set Response=@Reply,AnsDate=@DateOfReply where SenderAcc=@sSenderAccNo AND Subject=@sSubject", conn);
                comm.Parameters.Add(new SqlParameter("@Reply", sReply));
                comm.Parameters.Add(new SqlParameter("@DateOfReply", sDate));
                comm.Parameters.Add(new SqlParameter("@sSenderAccNo", sSenderAccNo));
                comm.Parameters.Add(new SqlParameter("@sSubject", sSubject));
                SqlDataReader r = comm.ExecuteReader();
                comm.Dispose();
                conn.Close();
                return true;
            }
            catch (Exception ex)
            {
                conn.Close();
                return false;
                throw new Exception(ex.ToString(), ex);
            }
        }
        //------------------------------------------------------------------------------------------------------------
        //Administrator
        //-------------------------------------------------------------------------------------------------------------

        [WebMethod]
        public String ViewLogs()
        {
            //Todo:Need to implement code to show Logs to ADMINISTRATOR
            int i = 0, j = 0;
            String logs = "", SendorAccNo = "", ReceiAccNo = "", Date_Time = "", Type = "", Amt = "";
            String AccNo = "", Date_t = "", RecAccNo = "", type = "", amnt = "";
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            //cursor required
            SqlCommand comm = new SqlCommand("select * from Logs order by Date_Time desc", conn);            
            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);
            SendorAccNo += "<SenderAccNo>";
            ReceiAccNo += "<ReceiAccNo>";
            Date_Time += "<Date_Time>";
            Type += "<Type>";
            Amt += "<Amount>";
            logs += "<Logs>";
            // now, we get data from each row in the table (like a cursor)
            foreach (DataRow row in table.Rows)
            {
                if (i == 0)
                {
                    j = 0;
                    AccNo = row[j].ToString(); j++;
                    Date_t = row[j].ToString(); j++;
                    RecAccNo = row[j].ToString(); j++;
                    type = row[j].ToString(); j++;
                    amnt = row[j].ToString();

                    SendorAccNo += AccNo.Trim();
                    ReceiAccNo += RecAccNo.Trim();
                    Date_Time += Date_t.Trim();
                    Type += type.Trim();
                    Amt += amnt.Trim();
                    i++;
                }
                else
                {
                    j = 0;
                    AccNo = row[j].ToString(); j++;
                    Date_t = row[j].ToString(); j++;
                    RecAccNo = row[j].ToString(); j++;
                    type = row[j].ToString(); j++;
                    amnt = row[j].ToString();

                    SendorAccNo += ";" + AccNo.Trim();
                    ReceiAccNo += ";" + RecAccNo.Trim();
                    Date_Time += ";" + Date_t.Trim();
                    Type += ";" + type.Trim();
                    Amt += ";" + amnt.Trim();

                }
            }
            comm.Dispose();
            conn.Close();

            SendorAccNo += "</SenderAccNo>";
            ReceiAccNo += "</ReceiAccNo>";
            Date_Time += "</Date_Time>";
            Type += "</Type>";
            Amt += "</Amount>";
            logs += SendorAccNo + ReceiAccNo + Date_Time + Type + Amt + "</Logs>";
            conn.Close();
            return logs;
            
        }
        //------------------------------------------------------------------------------------------------------------
        //Administrator
        //-------------------------------------------------------------------------------------------------------------

        [WebMethod]
        public Boolean DeleteUserAccount(String sAccNo)
        {
            //Todo: Need to implement code to manage accounts
            //Delete From Profile Table          
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("DELETE FROM Profile WHERE  (AccNo = @UserAcc)", conn);
            comm.Parameters.Add(new SqlParameter("@UserAcc", sAccNo));
            SqlDataReader r = comm.ExecuteReader();
            comm.Dispose();
            conn.Close();
            
            //Delete product associated with Vendor 
                conn.Open();
                comm = new SqlCommand("DELETE FROM Product WHERE (VendorAcc = @sUserAcc)", conn);
                comm.Parameters.Add(new SqlParameter("@sUserAcc", sAccNo));
                r = comm.ExecuteReader();
                comm.Dispose();
                conn.Close();

                return true;            
        }
        [WebMethod]
        public String DisplayQueryList()
        {
            //Todo:Need to implement code to show Query to ADMINISTRATOR
            String sQuery = "", sSenderAccNo = "", sAskedQuery = "", oDate = "",sSubject="";
            String sSender = "", sAskQuery = "", oDates = "", sSub="";
            int i = 1, j = 0;
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("SELECT     SenderAcc, Subject, Query, Response, SendingDate, AnsDate FROM Query WHERE (Response IS NULL)", conn);
            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);

            // now, we get data from each row in the table (like a cursor)
            sQuery += "<Query>";
            sSubject += "<Subject>";
            sSenderAccNo += "<SenderAccNo>";
            sAskedQuery += "<AskedQuery>";
            oDate += "<Date>";

            foreach (DataRow row in table.Rows)
            {
                // sSenderAccNo = ""; sAskedQuery = ""; Date_Time = "";
                j = 0;
                sSender = row[j].ToString();                j++;
                sSub = row[j].ToString(); j++;
                sAskQuery = row[j].ToString();
                j = j + 2;
                oDates = row[j].ToString();
                if (i == 1)
                {
                    sSenderAccNo += sSender.Trim();
                    sAskedQuery += sAskQuery.Trim();
                    oDate += oDates.Trim();
                    sSubject += sSub.Trim();
                    i++;
                }
                else
                {
                    sSenderAccNo += ";" + sSender.Trim();
                    sAskedQuery += ";" + sAskQuery.Trim();
                    oDate += ";" + oDates.Trim();
                    sSubject += ";" + sSub.Trim();
                }
            }
            sSenderAccNo += "</SenderAccNo>";
            sSubject += "</Subject>";
            sAskedQuery += "</AskedQuery>";
            oDate += "</Date>";
            sQuery += sSenderAccNo.Trim()+ sSubject.Trim()+ sAskedQuery.Trim() + oDate.Trim() + "</Query>";
            sQuery.Trim();
            conn.Close();
            return sQuery;
        }
        [WebMethod]
        public String DisplayQueryResponceToUser(String sAccNo)
        {
            //Todo:Need to implement code to show Query to CUSTOMER AND VENDOR
            String sQuery = "", sSenderAccNo = "", sAskedQuery = "", oDate = "", oAnsDate = "", sRespose = "", sSubject="";
            String sSender = "", sAskQuery = "", oDates = "",oAnsd="",sRep="",sSub="";
            int i = 1, j = 0;
            if (conn.State == ConnectionState.Open)
            {
                conn.Close();
            }
            conn.Open();
            SqlCommand comm = new SqlCommand("SELECT * FROM Query WHERE  (SenderAcc =@UserAcc) AND (Response <> 'NULL')", conn);
            comm.Parameters.Add(new SqlParameter("@UserAcc", sAccNo));
            SqlDataAdapter adapter = new SqlDataAdapter(comm);
            DataTable table = new DataTable("demodata");

            // fill the datatable with data from our stored procedure
            adapter.Fill(table);

            // now, we get data from each row in the table (like a cursor)
            sQuery += "<Query>";
            sSubject += "<Subject>";
            sSenderAccNo += "<SenderAccNo>";
            sAskedQuery += "<AskedQuery>";
            oDate += "<Date>";
            sRespose += "<Response>";
            oAnsDate += "<AnsDate>";

            foreach (DataRow row in table.Rows)
            {
                // sSenderAccNo = ""; sAskedQuery = ""; Date_Time = "";
                j = 0;
                sSender = row[j].ToString();j++;
                sSub = row[j].ToString(); j++;
                sAskQuery = row[j].ToString(); j++;
                sRep = row[j].ToString(); j++;
                oDates = row[j].ToString(); j++;
                oAnsd = row[j].ToString(); j++;
                if (i == 1)
                {
                    sSenderAccNo += sSender.Trim();
                    sSubject += sSub.Trim();
                    sAskedQuery += sAskQuery.Trim();
                    sRespose += sRep.Trim();
                    oDate += oDates.Trim();
                    oAnsDate += oAnsd.Trim();
                    i++;
                }
                else
                {
                    sSenderAccNo += ";" + sSender.Trim();
                    sSubject += ";"+sSub.Trim();
                    sAskedQuery += ";" + sAskQuery.Trim();
                    sRespose += ";"+ sRep.Trim();
                    oDate += ";" + oDates.Trim();
                    oAnsDate += ";"+oAnsd.Trim();
                }
            }            
            sSenderAccNo += "</SenderAccNo>";
            sSubject += "</Subject>";
            sAskedQuery += "</AskedQuery>";
            oDate += "</Date>";
            sRespose += "</Response>";
            oAnsDate += "</AnsDate>";
            sQuery += sSenderAccNo.Trim() + sSubject.Trim()  +sAskedQuery.Trim() + oDate.Trim() + sRespose.Trim() + oAnsDate.Trim() + "</Query>";
            sQuery.Trim();
            conn.Close();
            return sQuery;
        }
        //------------------------------------------------------------------------------------------------------------
        //Administrator VENDOR CUSTOMER
        //-------------------------------------------------------------------------------------------------------------
        [WebMethod]
        public Boolean ChangePassword(String sAccNo, String OldPassword, String NewPassword)
        {
            //Todo: Need to change password 
            try
            {
                //check that the username doesn't already exist
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
                conn.Open();
                SqlCommand comm = new SqlCommand("Select * from Profile where AccNo=@AccNo and Password=@OldPassword", conn);
                comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                comm.Parameters.Add(new SqlParameter("@OldPassword", OldPassword));
                SqlDataReader r = comm.ExecuteReader();
                bool res = r.HasRows;
                conn.Close();
                if (!res)
                {
                    return false;
                }

                conn.Open();
                comm = new SqlCommand("Update Profile set Password=@Password where AccNo=@AccNo", conn);
                comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                comm.Parameters.Add(new SqlParameter("@Password", NewPassword));

                r = comm.ExecuteReader();
                comm.Dispose();
                conn.Close();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.ToString(), ex);
            }
            finally
            {
                conn.Close();
            }
            return true;
        }//End of Web method

        [WebMethod]
        public Boolean RechargeAccount(String sAccNo, Double dMoneyPoints)
        {
            try
            {
                //check that the username doesn't already exist
                Double MPoint=0, Balance=0;
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
                conn.Open();
                SqlCommand comm = new SqlCommand("Select * from Bank where AccNo=@AccNo", conn);
                comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));                
                SqlDataReader r = comm.ExecuteReader();
                
                while (r.Read())
                {
                    Balance = r.GetDouble(1);
                }
                comm.Dispose();
                conn.Close();

                if (Balance < dMoneyPoints)
                    return false;
                else
                {
                    conn.Open();
                    comm = new SqlCommand("Select MoneyPoints from Profile where AccNo=@AccNo", conn);
                    comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                    r = comm.ExecuteReader();                                 
                
                    while (r.Read())
                    {
                        MPoint = r.GetDouble(0);
                    }
                    conn.Close();

                    Balance = Balance - dMoneyPoints;

                    //Upade Bank Table
                    conn.Open();
                    comm = new SqlCommand("Update Bank set Balance=@Balance where AccNo=@AccNo", conn);
                    comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                    comm.Parameters.Add(new SqlParameter("@Balance", Balance));
                    r = comm.ExecuteReader();
                    conn.Close();
                    //Update Profile
                    MPoint = MPoint + dMoneyPoints;
                    conn.Open();
                    comm = new SqlCommand("Update Profile set MoneyPoints=@Balance where AccNo=@AccNo", conn);
                    comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                    comm.Parameters.Add(new SqlParameter("@Balance", MPoint));
                    r = comm.ExecuteReader();
                    conn.Close();
                    return true;
                }
                
            }
            catch (Exception ex)
            {
                throw new Exception(ex.ToString(), ex);
            }
            finally
            {
                conn.Close();
            }     

        }
        //-----------------------------------------------------------------------
        //                BANK TRANSACTION
        //-----------------------------------------------------------------------
        [WebMethod]
        public Boolean Liquify(String sAccNo, Double dMoneyPoints)
        {
            try
            {
                //check that the username doesn't already exist
                Double MPoint = 0, Balance = 0;
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
                conn.Open();
                SqlCommand comm = new SqlCommand("Select MoneyPoints from Profile where AccNo=@AccNo", conn);
                comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                SqlDataReader r = comm.ExecuteReader();

                while (r.Read())
                {
                    MPoint = r.GetDouble(0);
                }
                conn.Close();

                if (MPoint < dMoneyPoints) {
                    return false;
                }
                else
                {
                
                    conn.Open();
                    comm = new SqlCommand("Select Balance from Bank where AccNo=@AccNo", conn);
                    comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                    r = comm.ExecuteReader();

                    while (r.Read())
                    {
                        Balance = r.GetDouble(0);
                    }
                    conn.Close();
                
                
                    Balance = Balance + dMoneyPoints;

                    //Upade Bank Table
                    conn.Open();
                    comm = new SqlCommand("Update Bank set Balance=@Balance where AccNo=@AccNo", conn);
                    comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                    comm.Parameters.Add(new SqlParameter("@Balance", Balance));
                    r = comm.ExecuteReader();
                    conn.Close();
                    //Update Profile

                    MPoint = MPoint - dMoneyPoints;
                    conn.Open();
                    comm = new SqlCommand("Update Profile set MoneyPoints=@Balance where AccNo=@AccNo", conn);
                    comm.Parameters.Add(new SqlParameter("@AccNo", sAccNo));
                    comm.Parameters.Add(new SqlParameter("@Balance", MPoint));
                    r = comm.ExecuteReader();
                    conn.Close();
                    return true;
                }

            }
            catch (Exception ex)
            {
                throw new Exception(ex.ToString(), ex);
            }
            finally
            {
                conn.Close();
            }

        }   

    }//End of Web service

}//End