final String LOGFILE = "/Users/kyrilosvourlakidis/tmp/index.log";
final String NODES = "/Users/kyrilosvourlakidis/i2Analyze/data/solr/clusters/is_cluster/nodes";


final String appendNodesSizeToLog = "du -s -k " + NODES + " >> " + LOGFILE;
runCommand(appendNodesSizeToLog);
//final String appendNumberOfRecordsToLog = "wc -l " + csvFile.getAbsolutePath() + " >> " + LOGFILE;
//runCommand(appendNumberOfRecordsToLog);

private void runCommand(String command)
{
    final Runtime rt = Runtime.getRuntime();
    try
    {
        System.out.println("Command: " + command);
        final Process pr = rt.exec(command);
        pr.waitFor();
    }
    catch (IOException | InterruptedException ex)
    {
        ex.printStackTrace();
    }
}