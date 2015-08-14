#!/bin/bash

# Clean out byte code
SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
cd $SCRIPT_DIR
rm *.class >/dev/null 2>/dev/null

# Compile
javac -encoding UTF-8 -cp ../compiled/jar/kanatools.jar SampleKanaConverter.java

# Run
java -Dfile.encoding=UTF-8 -cp ../compiled/jar/kanatools.jar:. SampleKanaConverter $1
