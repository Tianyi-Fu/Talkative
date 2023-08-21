#!/usr/bin/bash
echo "update logging configuration..."
sudo sh -c "echo '*.info;mail.none;authpriv.none;cron.none /dev/ttyS0' >> /etc/rsyslog.conf"
sudo systemctl restart rsyslog
ntpdate -u pool.ntp.org
ntpd -qg
echo logged in as $USER.
echo in directory $PWD

echo "installing MariaDB..."

sudo apt update

sudo apt -y install wget curl
sudo apt install unzip -y
sudo apt -y install git

echo "needs to be in root account"
cd root

touch .ssh/known_hosts
ssh-keyscan git.cardiff.ac.uk >> .ssh/known_hosts
chmod 644 .ssh/known_hosts

echo "now needs to be in debian user directory"
cd /home/debian


echo "installing gitlab deployment key..."
touch fty_keypair.key
cat << `EOF` >> fty_keypair.key
-----BEGIN OPENSSH PRIVATE KEY-----
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
-----END OPENSSH PRIVATE KEY-----
`EOF`
chmod 400 fty_keypair.key

echo "cloning repository..."

ssh-agent bash -c 'ssh-add fty_keypair.key;git clone git@git.cardiff.ac.uk:c22045328/devops_tianyi-fu.git'

echo "changing to repository directory..."
cd devops_tianyi-fu/

sudo apt update
sudo apt install default-jdk -y


sudo chown debian:debian /home/debian/fty_keypair.key
cp /home/debian/fty_keypair.key /home/debian/.ssh/id_rsa
sudo chown debian:debian /home/debian/.ssh/id_rsa
sudo chown debian:debian -R /home/debian/devops_tianyi-fu
cat << `EOF` >> /home/debian/.ssh/authorized_keys
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQC7osSlhKZ8dEkDC9RYj9CJVw0SKZdji9lVV+KJ83QOR2yeoPrDKxen4dYfaYD161Hha7LmRzUX5WqICNHF2NxXhJCeAkZuoGNt9uEQVb2N2dm0O9Q8bQSJ1KeS5/ASun7FNad7SluEApbiVe6lgxUX2VyovbPRV6wQCFFco9E5Wwt0EBUEBhaXTPDv3YzPYsUbcFMOuha1jTqWd46ZsvrSCKz76US7QuWXz5ucxW//gqjMlhESYs86fhwpW1Xexlpw6mbBDKUE3wrKfSEcHPMmdEwaO6A2XE8jjfxUFAYOaGIiuWyjudndGkmMwMVTINZyox1cxTijqlS9VpZLiZe8jXQBohBaVePAl/XhmicyMsvwChvL6KOXO24TG+Dnz6S31vPS3ChfNyIqa+eJNY3RpFCUO42n5ZVw5Xy6Ak6FeRv6ERE9Ew6jnvG/G7BYytmFd1+uuzto3DNzMzZllK1Dh3TsnQfE1+7uyDXEFskVOEjHRdR1poR5Y0fD6rW21Zf/RVxLH8V4HpHw5/BNpyK/QGOnd9mpdo8vrs+YnN6NdFXU5F2m0Xsrbr4uNMUNqQF6IbtfaEiUvhytXxifrq0GAhDd4kUZRBC7/DtKFUygD/3QjeUmcigkx0WKCxoW5ST5Ib2UZqQBwsPnxweT26CYE2dyEPDXhSeaNeO2+aJ13w== debian@jenkinsserverdebian

`EOF`

#-------------------------------------------------------------------------------
# Update package index and install dependencies
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
echo "---------------------------------------------------------------installed docker"
sed -i "s|^ExecStart.*$|ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock --mtu=1450|" /lib/systemd/system/docker.service

sudo systemctl daemon-reload
sudo systemctl enable --now docker

sudo ip link set docker0 mtu 1450

echo "---------------------------------------------------------------login"
sudo docker login -u tianyifu -p fty5005669

sudo docker compose up -d
sudo docker compose down


