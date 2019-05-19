package org.generic

interface IShellRegistry {
    String bashShell(String command)
    String bashShellOutput(String command)
    String bashShellStatus(String command)
    String batchScript(String command)
    String powerShellScript(String command)
}