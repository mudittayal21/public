`NOTE: This is valid only for 90 days.`
---

#### **`Step 1:`**
Go to [PunchSalad.com](https://punchsalad.com/ssl-certificate-generator/).

#### **`Step 2:`**
Enter the following detials in the form displayed. <br>
- Domain Name
- Email Address
- Select "HTTP" for Website
- Click "Generate SSL Certificate"

#### **`Step 3:`**
A new page opens, that displays you with two downloadable files. These are used by LetsEncrypt (Public Welfare Org) to verify that the domain is owned by you.

Create a folder ".well-known" in the root folder of your domain. And inside the ".well-known" create another folder "acme-challenge". Then upload the above file(s) inside the acme-challenge folder.

#### **`Step 4:`**
On the cPanel, search for SSL/TSL (Should be under the Security Tab) <br>
On the Right Panel, you will find "Manage SSL Sites" link (under "INSTALL AND MANAGE SSL FOR YOUR SITE (HTTPS)")

#### **`Step 5:`**
Scroll to "Install an SSL Website" and fill in all the details

- Domain
- Certificate
	- On the PunchSalad webiste, you will see that multiple certificates have been generated.
	- Copy the First Certificate and paste it in the "Certificate: (CRT)" area.
	- Copy the remaining certificates and paste them in the "Certificate Authority Bundle: (CABUNDLE)" area.
- Private Key
	- Copy the private key and paste it in the "Private Key" area.

- Click "Install Certificate". Then click "Okay".