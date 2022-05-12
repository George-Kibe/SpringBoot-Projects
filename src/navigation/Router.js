import React from 'react';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'

import LocationSearchScreen from '../screens/LocationSearch'
import GuestsScreen from '../screens/Guests'


const Stack = createStackNavigator()

const Router = () => {
  return (
    <SafeAreaProvider>
      <NavigationContainer>
        <Stack.Navigator>
            <Stack.Screen name="Location Search" component={GuestsScreen}
              options={{title:"Search by Locations"}} />
        </Stack.Navigator>
      </NavigationContainer>
    </SafeAreaProvider> 
  )
}

export default Router