mkdir resources/levels/CUSTOM
mkdir build
javac -d build --module-path javafx-sdk-11.0.2/lib $(find src/ -name "*.java") 
