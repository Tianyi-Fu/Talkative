variable "flavor" { default = "m1.large" }
variable "image" { default = "Debian 10.13.0 20220910" }
variable "name" { default = "DevProject" }

variable "keypair" { default = "cloudProject" } # you may need to change this
variable "network" { default = "cloudProject" }   # you need to change this

variable "pool" { default = "cscloud_private_floating" }
variable "server_script" { default = "./server.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "tf_MyProject_security" }
