## Installing Jenkins

1. Update the package list:
    ```sh
    sudo apt update
    ```

1. Install Java Development Kit (JDK):
    ```sh
    sudo apt install openjdk-17-jdk -y
    ```

1. Add the Jenkins repository key to your system:
    ```sh
    curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
    /usr/share/keyrings/jenkins-keyring.asc > /dev/null
    ```

1. Add the Jenkins repository to your package sources:
    ```sh
    echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
    https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
    /etc/apt/sources.list.d/jenkins.list > /dev/null
    ```

1. Update the Package List and Install:
    ```sh
    sudo apt-get update
    sudo apt-get install jenkins -y
    ```

1. Update the default PORT for jenkins from 8080, to some other port, since we have tomcat running on PORT 8080.
    ```sh
    cd /usr/lib/systemd/system
    sudo nano jenkins.service

    <!-- Update the PORT `HTTP_PORT` to 8081 and save the file. -->
    ```