package org.generic

interface IShellRegistry {
    String bashShell(String command)
    String batchScript(String command)
    String powerShellScript(String command)
}