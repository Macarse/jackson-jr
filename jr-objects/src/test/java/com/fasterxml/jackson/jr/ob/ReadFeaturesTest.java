package com.fasterxml.jackson.jr.ob;

import com.fasterxml.jackson.jr.ob.JSON;

public class ReadFeaturesTest extends TestBase
{
    static class IsBean {
        public boolean isEnabled() { return true; }

        public int getValue() { return 42; }
    }

    /*
    /**********************************************************************
    /* Test methdods
    /**********************************************************************
     */

    public void testPojoWithIsGetter() throws Exception
    {
        assertTrue(JSON.std.isEnabled(JSON.Feature.USE_IS_GETTERS));
        
        String json;

        json = JSON.std.asString(new IsBean());
        // by default, will use 'is-getter':
        assertEquals(aposToQuotes("{'enabled':true,'value':42}"), json);

        // but can disable
        json = JSON.std
                .without(JSON.Feature.USE_IS_GETTERS)
                .asString(new IsBean());
        assertEquals(aposToQuotes("{'value':42}"), json);
    }
}
