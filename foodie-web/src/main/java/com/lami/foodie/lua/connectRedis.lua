--
-- Created by IntelliJ IDEA.
-- User: xjk
-- Date: 1/7/18
-- Time: 22:06
-- To change this template use File | Settings | File Templates.
--

local redis = require 'redis'

local redis = require 'redis'
local client = redis.connect('127.0.0.1', 6379)
local response = client:ping()           -- true