import java.nio.file.Files

def testFile = new File('test')

println testFile.absolutePath
println testFile.toPath()
println testFile.exists()
testFile.write 'groovy test file'

testFile << '\nanother way to write to a file'

testFile.text += '''
    append a triple quoted string using the text property
'''

println testFile.text

def backupFile = new File('test.bak')
println backupFile.exists()

Files.copy(testFile.toPath(), backupFile.toPath())

println backupFile.exists()