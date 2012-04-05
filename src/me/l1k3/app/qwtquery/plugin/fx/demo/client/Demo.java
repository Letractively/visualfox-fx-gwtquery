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
package me.l1k3.app.qwtquery.plugin.fx.demo.client;

import static com.google.gwt.query.client.GQuery.*;
import static me.l1k3.gwtquery.plugin.fx.client.Fx.FX;
import me.l1k3.core.client.Color;
import me.l1k3.core.client.Core;
import me.l1k3.fx.client.*;
import me.l1k3.fx.client.channel.ColorChannel;
import me.l1k3.fx.client.channel.inter.DimensionConstant.Direction;
import me.l1k3.fx.client.inter.FX;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;

public class Demo implements EntryPoint {
    private int counter;
    
    public void onModuleLoad() {
        FXCallback callback = new FXCallback();
                
        //optimal visualfox-fx pattern - recycle recycle and recycle
        $("#recycle").append("<h3>Recycling is good<h3>");
        $("#recycle").as(FX).fade(0.01, 1).animate(4000).callback(new Command() {
            @Override
            public void execute() {
                $("#recycle").as(FX).animate(4000).callback(this);
            }
        });
        
        //so-so pattern where we don't recycle anything - best for one time animation
        $("#text").prepend("<h1>Visualfox-fx <span style=\"color:white;\"><3</span> GwtQuery</h1>");
        counter = -1;
        callback.execute();
        
        //loops
        $("body").as(FX).animate(new FXLoops(new FXChannel().add(ColorChannel.rangeBackground(new Color(255,255,255), new Color(255,255,0))).setEasing("linear"), FXLoops.INFINITY, 0, true), 5000);
    }
    
    private class FXCallback implements Command {
        @Override
        public void execute() {
            counter++;
            
            if(counter==12) {
                counter = 0;
            }
            
            if(counter<2) {
                FX fx = new FXClip(Direction.HORIZONTAL);
                $("#info").text(counter+"/12 "+fx.toString());
                $("#text").as(FX).animate(fx, 1000).callback(this);
            }
            else 
            if(counter==2) {
                String currentColor = Core.getComputedStyleProperty($("#text h1").get(0), "backgroundColor");
                FX fx = new FXHighlight(Color.parse(currentColor), new Color(0,255,0));
                $("#info").text(counter+"/12 "+fx.toString());
                $("#text h1").as(FX).animate(fx, 1000, 2500).callback(this);
            }
            else
            if(counter<5) {
                FX fx = new FXFade();
                $("#info").text(counter+"/12 "+fx.toString());
                $("#text").as(FX).animate(fx, 1000).callback(this);
            }
            else 
            if(counter==5) {
                String currentColor = Core.getComputedStyleProperty($("#text h1").get(0), "backgroundColor");
                FX fx = new FXHighlight(Color.parse(currentColor), new Color(255,0,0));
                $("#info").text(counter+"/12 "+fx.toString());
                $("#text h1").as(FX).animate(fx, 1000, 2500).callback(this);
            }
            else
            if(counter<8) {
                FX fx = new FXOpen();
                $("#info").text(counter+"/12 "+fx.toString());
                $("#text").as(FX).animate(fx, 1000).callback(this);
            }
            else 
            if(counter==8) {
                String currentColor = Core.getComputedStyleProperty($("#text h1").get(0), "backgroundColor");
                FX fx = new FXHighlight(Color.parse(currentColor), new Color(0,0,255));
                $("#info").text(counter+"/12 "+fx.toString());
                $("#text h1").as(FX).animate(fx, 1000, 2500).callback(this);
            }
            else
            if(counter<11) {
                FX fx = new FXReveal();
                $("#info").text(counter+"/12 "+fx.toString());
                $("#text").as(FX).animate(new FXReveal(), 1000).callback(this);
            }
            else 
            if(counter==11) {
                String currentColor = Core.getComputedStyleProperty($("#text h1").get(0), "backgroundColor");
                FX fx = new FXHighlight(Color.parse(currentColor), new Color(255,0,255));
                $("#info").text(counter+"/12 "+fx.toString());
                $("#text h1").as(FX).animate(fx, 1000, 2500).callback(this);
            }
        }
    }
}
