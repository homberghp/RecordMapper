#!/bin/bash
scriptdir=$(dirname $0)
## make sure the class files are in target
if [ ! -e ../genericmapper/target/genericmapper.jar ]; then
    echo building genericmapper jar
    (cd ../genericmapper/; mvn -q package)
fi
annojar=${HOME}/.m2/repository/nl/fontys/sebivenlo/sebiannotations/1.0-SNAPSHOT/sebiannotations-1.0-SNAPSHOT.jar
mkdir -p out/{classes,src/main/java}
#javac -p ../genericmapper/target/genericmapper.jar:${annojar}  -d target/classes src/main/java/**/*.java
mvn -q package
classes=""
for c in $(find target/classes/sampleentities -name "*.class" | grep -v "Mapper.class" | sed -e's#target/classes/##g;s#/#.#g;s#.class##') ; do
    classes="${classes} ${c}"
done

java --module-path  ${scriptdir}/../genericmapper/target/genericmapper.jar:${annojar}:target/sampleentities-1.0-SNAPSHOT.jar  --add-modules ALL-MODULE-PATH -m genericmapper/genericmapper.MapperGenerator $(echo ${classes})
cat <<EOF > out/src/main/java/module-info.java
module generatedmappers {
   requires genericmapper;
   requires sampleentities;
   exports generatedmappers;   
}
EOF

javac -d out/classes -p ../genericmapper/target/genericmapper.jar:target/classes:${HOME}/.m2/repository/nl/fontys/sebivenlo/sebiannotations/1.0-SNAPSHOT/sebiannotations-1.0-SNAPSHOT.jar out/src/main/java/module-info.java out/src/main/java/**/*.java

jar cf out/generatedmappers.jar -C out/classes/ .
