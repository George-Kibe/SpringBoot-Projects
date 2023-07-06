/* eslint-disable react/react-in-jsx-scope */
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {SafeAreaProvider} from 'react-native-safe-area-context';
import {
  Walkthrough,
  Verification,
  ProfileAccount,
  PhoneNumber,
  PersonalChat,
} from './src/screens';
import BottomTabNavigation from './src/navigation/BottomTabNavigation';
import {Provider} from 'react-redux';
import store from './redux/store';
const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <Provider store={store}>
      <SafeAreaProvider>
        <NavigationContainer>
          <Stack.Navigator
            screenOptions={{
              headerShown: false,
            }}
            initialRouteName="Walkthrough">
            <Stack.Screen
              name="BottomTabNavigation"
              component={BottomTabNavigation}
            />
            <Stack.Screen name="Walkthrough" component={Walkthrough} />
            <Stack.Screen name="Verification" component={Verification} />
            <Stack.Screen name="ProfileAccount" component={ProfileAccount} />
            <Stack.Screen name="PhoneNumber" component={PhoneNumber} />
            <Stack.Screen name="PersonalChat" component={PersonalChat} />
          </Stack.Navigator>
        </NavigationContainer>
      </SafeAreaProvider>
    </Provider>
  );
}
