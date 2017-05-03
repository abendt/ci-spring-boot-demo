# Spring Boot, Maven and Concourse

## Setup

### Prerequisites

```bash
$ brew install git
$ brew cask install virtualbox
$ brew cask install vagrant
$ brew cask install java
```

### Setting up the VM

```bash
$ cd ~/workspace
$ mkdir concourse
$ vagrant init concourse/lite
$ vagrant up
```

### Setting up the Command Line Tool

- Navigate to <http://192.168.100.4:8080>
- Download the CLI

```bash
$ cp ~/Downloads/fly /usr/local/bin
$ chmod u+x /usr/local/bin/fly
$ fly -t lite login -c http://192.168.100.4:8080
```

### Adding the Project to Concourse

```bash
$ cd ~/workspace
$ git clone https://github.com/spilth/concourse-maven-spring-boot.git
$ cd concourse-maven-spring-boot
$ fly -t lite set-pipeline -p concourse-maven-spring-boot -c concourse/pipeline.yml
$ fly -t lite unpause-pipeline -p concourse-maven-spring-boot
```

## Resources

- <https://www.vagrantup.com/>
- <https://www.virtualbox.org/>
- <https://github.com/concourse/concourse>
- <https://github.com/nitram509/concourse-java-maven-test-prj>
