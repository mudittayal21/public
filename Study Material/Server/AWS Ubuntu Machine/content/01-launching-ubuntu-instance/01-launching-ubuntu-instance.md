## Launching EC2 Instance (Ubuntu)

`Amazon EC2 refers to Amazon Elastic Compute Cloud (Amazon EC2)` provides on-demand, scalable computing capacity in the Amazon Web Services (AWS) Cloud. Using Amazon EC2 reduces hardware costs so you can develop and deploy applications faster.

### **`Step 1`**
Login to your `AWS Console` and in the search bar above search `EC2`.
![Search EC2 in AWS Console](./images/01.png)
---

### **`Step 2`**
Click on `EC2`, this will open your EC2 dashboard on the console.
![EC2 Dashboard](./images/02.png)
---

### **`Step 3`**
1. On the dashboard, select `Instances (Running)`.
![Instances Table](./images/03.png)
---

### **`Step 4`**
1. As you can see above, I already have one running instance on my console. However, we would be `creating a new console, configure it step-by-step and replace this running instance`. Click on `Launch Instance` to open the `Create Instance Wizard`.
    
    - Enter the `Instance Name`
    ![Instance Name](./images/04.png)

    - Select the `Application and OS Images (AMI - Amazon Machine Image)`
    ![AMI](./images/05.png)

    - Select the `Instance Type`
    ![Instance Type](./images/06.png)

    - Select the `Key Pair` or `Generate a New One`.
    ![Key Pair](./images/07.png)
    To create a new `Key Pair` click on `Click new key pair`.
    ![Click new key pair](./images/08.png)
    Once you click on `Create key pair`, this would download the `.pem` file, keep this file safe as this would be used to login to the server later.
    ![Select the Key Pair](./images/09.png)

    - Configure the `Network Settings`.

        - Click on the `Edit` button, to unlock additional features.
        ![Edit Network Settings](./images/10.png)

        - Enter the `Security Group Name` and `Description`.
        ![Name and Description](./images/11.png)
        ![Inbound Rules](./images/12.png)

    - Configure the `Storage`. In the free tier, upto 30 GB of storage is free.
    ![Storage Settings](./images/13.png)

    - Click on `Launch Instance`.
    ![Click Launch](./images/14.png)
---

### **`Step 5`**
1. Once you create the Instnace, initially it would be in the `Pending State`.
![Pending State](./images/15.png)
After sometime, the state of the Instance would change to `Running` and Status Check will show `2/2 checks passed`.
![Running State](./images/16.png)
---

### **`Step 6`**
1. If you open the IP Address right now, you'll get an error saying `The site cant be reached`.
![IP Address Error](./images/17.png)
![IP Address Error](./images/18.png)

`In the next step, we would be installing Apache2 on the server and adding our first HTML file`.

[**Next Page ( Installing Apache2 )**](./02-installing-apache2.md)

---