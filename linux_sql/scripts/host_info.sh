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
lscpu_out=$(lscpu)
hostname=$(hostname)

#Retrieve hardware specification variables
#xargs is a trick to trim leading and trailing white spaces
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep 'Model name' |awk '{$1=$2="";print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep 'CPU MHz' |awk '{$1=$2="";print $0}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep 'L2 cache' |awk '{print $3}' | sed 's/.$//' | xargs)
total_mem=$(grep MemTotal /proc/meminfo | awk '{print $2}' | sed 's/.$//' | xargs)
timestamp=$(date +'%Y-%m-%d %H:%M:%S ')



#PSQL command: Inserts server usage data into host_usage table
#Note: be careful with double and single quotes
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp)
VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp');"

#set up env var for pql cmd
export PGPASSWORD=$psql_password
#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?