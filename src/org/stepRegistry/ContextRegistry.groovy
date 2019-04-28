package org.stepRegistry

import org.generic.IContext
import org.generic.MetaCode

class ContextRegistry implements Serializable {
    private static IContext _context

    static void registerContext(IContext context) {
        _context = context
    }

    static void registerDefaultContext(Object steps) {
        _context = new DefaultContext(steps)
        MetaCode.baseBuiltinTypes()
    }

    static IContext getContext() {
        return _context
    }
}
