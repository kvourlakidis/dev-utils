#!/bin/bash

logfile=~/copy.log
device=/dev/sr0
outdir=/mnt/zfs/main/ISO
outname='minority-report.iso'
blocksize=2048
volumesize=3926968

# isoinfo -d -i /dev/sr0
# validate device is present
# validate device type
# validate device block size
# validate device volume size

# dd if=$device of=$outdir/$outname bs=$blocksize count=$volumesize status=progress
dd if=$device of=$outdir/$outname bs=$blocksize count=$volumesize status=progress > $logfile
