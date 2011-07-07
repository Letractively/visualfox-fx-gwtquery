/*
 * Copyright 2011 Philippe Blanc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package me.l1k3.gwtquery.plugin.fx.client;

import me.l1k3.core.client.queue.Queue;
import me.l1k3.fx.client.FXFade;
import me.l1k3.fx.client.inter.FX;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.plugins.Plugin;
import com.google.gwt.user.client.Command;

public class Fx extends GQuery implements Queue, Command {
    public static final Class<Fx> FX = Fx.class;
    
    private FX fx;
    private Command callback;
    
    static {
        GQuery.registerPlugin(Fx.class, new Plugin<Fx>() {
          public Fx init(GQuery gq) {
            return new Fx(gq);
          }
        });
    }
    
    protected Fx(GQuery gq) {
        super(gq);
    }
    
    public Fx fx(FX fx) {
        this.fx = fx;
        fx.init(get(0));
        data("FX", fx);
        
        return this;
    }
    
    public Fx fade(double start, double end) {
        if(get(0)!=null)
        fx(FXFade.range(get(0), start, end));
        
        return this;
    }
    
    public FX getFx() {
        return fx!=null? fx : data("FX", FX.class);
    }
    
    public Queue animate() {
        FX fx = getFx();
       
        if(fx!=null)
        fx.animate(500).callback(this);
        
        return this;
    }
    
    public Queue animate(int duration) {
        FX fx = getFx();
        
        if(fx!=null)
        fx.animate(duration).callback(this);
        
        return this;
    }
    
    public Queue animate(int delay, int duration) {
        FX fx = getFx();
        
        if(fx!=null)
        fx.animate(duration, delay, false).callback(this);
        
        return this;
    }
    
    public Queue animate(FX fx) {
        if(get(0)!=null)
        fx.init(this.get(0)).animate(500).callback(this);
        
        return this;
    }
    
    public Queue animate(FX fx, int duration) {
        if(get(0)!=null)
        fx.init(this.get(0)).animate(duration).callback(this);
        
        return this;
    }
    
    public Queue animate(FX fx, int delay, int duration) {
        if(get(0)!=null)
        fx.init(this.get(0)).animate(duration, delay, false).callback(this);
        
        return this;
    }

    @Override
    public void callback(Command callback) {
        this.callback = callback;
    }

    @Override
    public void execute() {
        if(callback!=null) {
            Scheduler.get().scheduleDeferred(callback);
        }
    }
}
