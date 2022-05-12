import React from 'react';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'
import HomeTabNavigator from './HomeTabNavigator';

import LocationSearchScreen from '../screens/LocationSearch'
import GuestsScreen from '../screens/Guests'


const Stack = createStackNavigator()

const Router = () => {
  return (
    <SafeAreaProvider>
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen name="Home" component={HomeTabNavigator}
            options={{headerShown:false}} />
          <Stack.Screen name="Location Search" component={LocationSearchScreen}
            options={{title:"Search by Locations"}} />
          {/* <Stack.Screen name="Accommodation Details" component={GuestsScreen}
            options={{title:"Accommodation Details"}} /> */}
        </Stack.Navigator>
      </NavigationContainer>
    </SafeAreaProvider> 
  )
}

export default Router