#!/bin/bash

DATE=$1
aws s3api list-objects-v2 --bucket maximsherstoboyevtask2  --query 'Contents[?LastModified>=`'"$DATE"'`].Key' --profile ec2_admin