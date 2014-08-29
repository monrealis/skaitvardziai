#!/bin/bash
set -e

./make-gcj-exec.sh

ULB=/usr/local/bin/
CP="cp -av target/skaiciai $ULB"
if [ -w $ULB ] ; then
	$CP
else
	echo $ULB not writable, installing with sudo
	sudo $CP
fi

echo Installation succeeded
if [ $(skaiciai 1) == "vienas" ] ; then
	echo Test passed. Time to have fun.
	echo Counting from -5 to 20
	seq -5 20 | skaiciai | paste -s -d, - | sed "s/,/, /g"
	BIG=$(( $RANDOM ** 4 ))
	printf "Quiz: is it true that %s = %s?\n" $BIG "$(skaiciai $BIG)"
else
	echo Test failed
	exit 1
fi