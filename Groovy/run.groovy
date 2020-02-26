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