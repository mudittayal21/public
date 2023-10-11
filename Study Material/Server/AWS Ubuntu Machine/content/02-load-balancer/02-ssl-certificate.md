## Creating a Free Public SSL Certificate

### **`Step 1`**
In the search box above, search for `Certificate Manager`. Click on `Certificate Manager` to open the `AWS Certificate Manager Console`.
![Search Certificate Manager](./images/07.png)

---

### **`Step 2`**
You can view all the issued certificates here. Click on `Request` to create a new one.
![Search Certificate Manager](./images/08.png)

---

### **`Step 3`**
Select `Request a Public Ceryificate`. Click `Next`.
![Search Certificate Manager](./images/09.png)

---

### **`Step 4`**
Enter the domain names against which you want the certificate to be created. <br>
Enter one `without any subdomain` and the other one with a `wildcard (*)` to secure all the subdomains. <br>
Leave all the other settings as it is and click `Create`.
![Search Certificate Manager](./images/10.png)

---

### **`Step 5`**
Initially the certificate would show `Pending Validation`.
![Search Certificate Manager](./images/11.png)

After sometime the same would change to `Issued`.
![Search Certificate Manager](./images/12.png)

---