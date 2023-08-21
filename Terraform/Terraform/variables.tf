variable "flavor" { default = "m1.xlarge" }
variable "image" { default = "Rocky Linux 9 20220830" }
variable "name" { default = "Talkative" }

variable "keypair" { default = "cloudProject" } # you may need to change this
variable "network" { default = "cloudProject" }   # you need to change this

variable "pool" { default = "cscloud_private_floating" }
variable "server_script" { default = "./server.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "tf_MyProject_Original_security" }
