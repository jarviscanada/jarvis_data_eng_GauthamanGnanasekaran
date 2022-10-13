#!/bin/bash
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check # of args
if [ $# -ne  5 ]; then
  echo "Please enter the correct number of arguments (psql_host, psql_port, db_name, psql_user, psql_password)"
  exit 1
fi

#Save machine statistics in MB and current machine hostname to variables
vmout=$(vmstat --unit M)
hostname=$(hostname)

#Retrieve hardware specification variables
#xargs is a trick to trim leading and trailing white spaces
memory_free=$(echo "$vmout" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmout"  | grep -P "\d+" | awk '{print $15}' | xargs)
cpu_kernel=$(echo "$vmout"  | grep -P "\d+" | awk '{print $14}' | xargs)

disk_io=$(vmstat -d | grep -P "\d+" | awk '{print $10}' | xargs)
disk_available=$(df -m --total | grep "total" | awk '{print $4}' | xargs)

#Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(date +'%Y-%m-%d %H:%M:%S ')

#Subquery to find matching id in host_info table
#host_id=$("SELECT id FROM host_info WHERE hostname='$hostname';")

#PSQL command: Inserts server usage data into host_usage table
#Note: be careful with double and single quotes
insert_stmt="INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES('$timestamp', (SELECT id FROM host_info WHERE host_info.hostname = '$hostname'), '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');"

#set up env var for pql cmd
export PGPASSWORD=$psql_password
#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?