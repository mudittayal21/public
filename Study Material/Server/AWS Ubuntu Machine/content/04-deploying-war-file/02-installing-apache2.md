## Installing Apache2

### `Enabling Reverse Proxy`

Running tomcat on port 80 requires administrative priviledges because port 80 is a priviledged port. Thus, we would be using a reverse proxy to forward all the requests to Tomcat 8080 port.

1. Install Apache HTTP Server
    ```sh
    sudo apt install apache2 -y
    ```

1. Enable the `proxy` and `proxy_http` modules by running the below commands:
    ```sh
    sudo a2enmod proxy
    sudo a2enmod proxy_http
    ```

1. Create a virtual host configuration file for your Tomcat application.
    ```sh
    sudo nano /etc/apache2/sites-available/test.viermonk.com.conf
    ```

    Enter the below configuration.
    ```sh
    <VirtualHost *:80>
      ServerName test.viermonk.com
      ProxyPass / http://localhost:8080/web/
      ProxyPassReverse / http://localhost:8080/web/
    </VirtualHost>
    ```

1. Disbale the default and enable the new virtual host configuration:
    ```sh
    sudo a2ensite test.viermonk.com.conf
    sudo a2dissite 000-default.conf
    ```

1. Reload Apache2 to apply the changes
    ```sh
    sudo systemctl reload apache2
    ```

---